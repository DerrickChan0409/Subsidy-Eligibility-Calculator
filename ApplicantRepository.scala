package storage

import model._

import scala.collection.mutable.ListBuffer
import java.io.{File, PrintWriter}
import scala.io.Source

object ApplicantRepository {

  private val fileName = "records.csv"

  private val records = ListBuffer[ApplicationRecord]()

  // Load existing records when the application starts
  loadFromFile()

  // Add a new record
  def addRecord(record: ApplicationRecord): Unit = {
    records += record
    saveToFile()
  }

  // Return all records
  def getAllRecords: List[ApplicationRecord] = {
    records.toList
  }

  // Remove all records
  def clearRecords(): Unit = {
    records.clear()
    saveToFile()
  }

  // Save records to CSV
  private def saveToFile(): Unit = {

    val writer = new PrintWriter(new File(fileName))

    writer.println(
      "Name,IC Number,Age,Income,Dependents,Employment,Disability,Subsidies,Total Benefit"
    )

    records.foreach { record =>

      writer.println(

        s"${record.applicant.name}," +
          s"${record.applicant.icNumber}," +
          s"${record.applicant.age}," +
          s"${record.applicant.monthlyIncome}," +
          s"${record.applicant.dependents}," +
          s"${record.applicant.employmentStatus}," +
          s"${record.applicant.disabilityStatus}," +
          s"${record.eligibleSubsidies.map(_.name).mkString("|")}," +
          s"${record.totalBenefit}"

      )

    }

    writer.close()

  }

  // Load records from CSV
  private def loadFromFile(): Unit = {

    val file = new File(fileName)

    if (!file.exists()) return

    records.clear()

    val lines = Source.fromFile(file).getLines().drop(1)

    lines.foreach { line =>

      try {

        val data = line.split(",")

        if (data.length >= 9) {

          val applicant = Applicant(

            name = data(0),

            icNumber = data(1),

            age = data(2).toInt,

            monthlyIncome = data(3).toDouble,

            dependents = data(4).toInt,

            employmentStatus = data(5),

            disabilityStatus = data(6).toBoolean

          )

          val subsidyNames = data(7).split("\\|").toList

          val subsidies = subsidyNames.flatMap {

            case "Financial Aid" =>
              Some(new FinancialAid())

            case "Education Aid" =>
              Some(new EducationAid())

            case "Disability Aid" =>
              Some(new DisabilityAid())

            case "Senior Aid" =>
              Some(new SeniorAid())

            case _ =>
              None

          }

          val totalBenefit = data(8).toDouble

          records += ApplicationRecord(
            applicant,
            subsidies,
            totalBenefit
          )

        }

      }

      catch {

        case _: Exception =>
          println(s"Skipping invalid record: $line")

      }

    }

  }

}