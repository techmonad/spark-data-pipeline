package com.techmonad.pipeline.util

import com.univocity.parsers.csv.{CsvParser, CsvParserSettings}


class CSVParser(delimiter: Char = ',') {

  private val settings = new CsvParserSettings
  private val format = settings.getFormat
  format.setDelimiter(delimiter)
  format.setComment('\0')
  settings.setIgnoreLeadingWhitespaces(true)
  settings.setIgnoreTrailingWhitespaces(true)
  settings.setNullValue("")
  settings.setMaxCharsPerColumn(-1)
  settings.setMaxColumns(20000)

  private val parser = new CsvParser(settings)

  def parse(line: String): Array[String] =
    parser.parseLine(line)

}

