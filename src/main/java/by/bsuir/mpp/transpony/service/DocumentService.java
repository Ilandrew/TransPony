package by.bsuir.mpp.transpony.service;

import by.bsuir.mpp.transpony.entity.Cargo;
import by.bsuir.mpp.transpony.entity.CheckPoint;
import by.bsuir.mpp.transpony.entity.document.DocumentFormat;
import by.bsuir.mpp.transpony.entity.document.DocumentType;
import by.bsuir.mpp.transpony.entity.Trip;
import by.bsuir.mpp.transpony.entity.Waybill;
import com.itextpdf.text.*;
import com.itextpdf.text.Font;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;


import java.io.*;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.List;

public class DocumentService {
    private static final String OUT_FUEL_CONSUMPTION = "/Users/Andrew/Develop/fuel_consumption";
    private static final String OUT_PROFIT = "/Users/Andrew/Develop/profit";
    private static final String OUT_ROUTE = "/Users/Andrew/Develop/route";
    private static final String OUT_WAYBILL = "/Users/Andrew/Develop/waybill";
    private static final String OUT_STATUS_TRIP = "/Users/Andrew/Develop/status_trip";

    private static CellStyle defaultStyle;
    private static CellStyle redStyle;
    private static CellStyle greenStyle;
    private static CellStyle headerStyle;
    private static CellStyle bottomStyle;
    private static CellStyle whiteStyle;

    private static void createCellStyle(Workbook book) {
        defaultStyle = book.createCellStyle();

        defaultStyle.setBorderBottom(CellStyle.BORDER_THIN);
        defaultStyle.setBorderLeft(CellStyle.BORDER_THIN);
        defaultStyle.setBorderTop(CellStyle.BORDER_THIN);
        defaultStyle.setBorderRight(CellStyle.BORDER_THIN);
        defaultStyle.setFillForegroundColor(IndexedColors.GREY_25_PERCENT.getIndex());
        defaultStyle.setFillPattern(XSSFCellStyle.SOLID_FOREGROUND);

        redStyle = book.createCellStyle();
        redStyle.setBorderBottom(CellStyle.BORDER_THIN);
        redStyle.setBorderLeft(CellStyle.BORDER_THIN);
        redStyle.setBorderTop(CellStyle.BORDER_THIN);
        redStyle.setBorderRight(CellStyle.BORDER_THIN);
        redStyle.setFillForegroundColor(IndexedColors.RED.getIndex());
        redStyle.setFillPattern(XSSFCellStyle.SOLID_FOREGROUND);

        greenStyle = book.createCellStyle();

        greenStyle.setBorderBottom(CellStyle.BORDER_THIN);
        greenStyle.setBorderLeft(CellStyle.BORDER_THIN);
        greenStyle.setBorderTop(CellStyle.BORDER_THIN);
        greenStyle.setBorderRight(CellStyle.BORDER_THIN);
        greenStyle.setFillForegroundColor(IndexedColors.GREEN.getIndex());
        greenStyle.setFillPattern(XSSFCellStyle.SOLID_FOREGROUND);

        headerStyle = book.createCellStyle();

        headerStyle.setBorderBottom(CellStyle.BORDER_THIN);
        headerStyle.setBorderLeft(CellStyle.BORDER_THIN);
        headerStyle.setBorderTop(CellStyle.BORDER_THIN);
        headerStyle.setBorderRight(CellStyle.BORDER_THIN);
        headerStyle.setFillForegroundColor(IndexedColors.GREY_50_PERCENT.getIndex());
        headerStyle.setFillPattern(XSSFCellStyle.SOLID_FOREGROUND);

        bottomStyle = book.createCellStyle();

        bottomStyle.setBorderBottom(CellStyle.BORDER_THIN);
        bottomStyle.setBorderLeft(CellStyle.BORDER_THIN);
        bottomStyle.setBorderTop(CellStyle.BORDER_THIN);
        bottomStyle.setBorderRight(CellStyle.BORDER_THIN);
        bottomStyle.setFillForegroundColor(IndexedColors.GREY_40_PERCENT.getIndex());
        bottomStyle.setFillPattern(XSSFCellStyle.SOLID_FOREGROUND);

        whiteStyle = book.createCellStyle();

        whiteStyle.setBorderBottom(CellStyle.BORDER_THIN);
        whiteStyle.setBorderTop(CellStyle.BORDER_THIN);
        whiteStyle.setFillForegroundColor(IndexedColors.WHITE.getIndex());
        whiteStyle.setFillPattern(XSSFCellStyle.SOLID_FOREGROUND);
    }

    private static String saveGeneratedDocument(DocumentType type, DocumentFormat format) {
        switch (format) {
            case XLSX:
                return type.getPath() + format.getExtension();
            case CSV:
                return saveCSV(type.getPath());
            case PDF:
                return savePDF(type.getPath());
        }
        return null;
    }


    public static String getDocumentFuelConsumption(DocumentFormat format) {
        createDocumentFuelConsumption();
        return saveGeneratedDocument(DocumentType.FUEL_CONSUMPTION, format);
    }

    public static String getDocumentProfit(DocumentFormat format) {
        createDocumentProfit();
        return saveGeneratedDocument(DocumentType.PROFIT, format);
    }

    public static String getDocumentRoute( Integer id, DocumentFormat format) {
        createDocumentRoute(id);
        return saveGeneratedDocument(DocumentType.ROUTE, format);
    }

    public static String getDocumentWaybill(Integer id, DocumentFormat format) {
        createDocumentWaybill(id);
        return saveGeneratedDocument(DocumentType.WAYBILL, format);
    }

    public static String getDocumentStatusTrip(DocumentFormat format) {
        createDocumentStatusTrip();
        return saveGeneratedDocument(DocumentType.STATUS_TRIP, format);
    }

    private static void createDocumentFuelConsumption() {
        FileOutputStream os = null;
        Workbook book = null;
        try {
            List<Trip> trips = TripService.getAll();
            int rowNumber = 0;
            book = new XSSFWorkbook();
            createCellStyle(book);
            Sheet sheet = book.createSheet("Fuel Consumption");
            Row row;
            Cell cell;
            Double totalExpectedFuelConsumption = 0.0;
            Double totalRealFuelConsumption = 0.0;
            Double expectedFuel;
            Double realFuel;
            String[] header = {"id", "Ожидаемый расход топлива", "Реальный расход топлива", "Сумма", "Водитель", "Логист"};

            row = sheet.createRow(rowNumber++);
            for (int i = 0; i < header.length; i++) {
                cell = row.createCell(i);
                cell.setCellStyle(headerStyle);
                cell.setCellValue(header[i]);
            }

            for (Trip trip : trips) {
                if ("Завершён".equals(trip.getTripStatus())) {
                    row = sheet.createRow(rowNumber++);
                    expectedFuel = trip.getExpectedFuelConsumption().doubleValue();
                    realFuel = trip.getRealFuelConsumption().doubleValue();
                    for (int cellNumber = 0; cellNumber < 6; cellNumber++) {
                        cell = row.createCell(cellNumber);
                        cell.setCellStyle(defaultStyle);
                        if (cellNumber == 0) {
                            cell.setCellValue(trip.getId());
                        } else if (cellNumber == 1) {
                            cell.setCellValue(expectedFuel);
                        } else if (cellNumber == 2) {
                            cell.setCellValue(realFuel);
                        } else if (cellNumber == 3) {
                            if (expectedFuel - realFuel < 0) {
                                cell.setCellStyle(redStyle);
                            } else {
                                cell.setCellStyle(greenStyle);
                            }
                            cell.setCellValue(expectedFuel - realFuel);
                        } else if (cellNumber == 4) {
                            cell.setCellValue(trip.getDriver().getInitials());
                        } else if (cellNumber == 5) {
                            cell.setCellValue(trip.getRoute().getOwner().getInitials());
                        }
                    }
                    totalExpectedFuelConsumption += expectedFuel;
                    totalRealFuelConsumption += realFuel;
                }
            }
            row = sheet.createRow(rowNumber);
            List<Object> footer = new ArrayList<>();
            footer.add("Итого");
            footer.add(totalExpectedFuelConsumption);
            footer.add(totalRealFuelConsumption);
            footer.add(totalExpectedFuelConsumption - totalRealFuelConsumption);

            for (int i = 0; i < footer.size(); i++) {
                cell = row.createCell(i);
                cell.setCellStyle(bottomStyle);
                if (footer.get(i) instanceof Double) {
                    cell.setCellValue((Double) footer.get(i));
                } else if (footer.get(i) instanceof String) {
                    cell.setCellValue((String) footer.get(i));
                }
                if (i == 3) {
                    if((Double) footer.get(i) < 0) {
                        cell.setCellStyle(redStyle);
                    } else {
                        cell.setCellStyle(greenStyle);
                    }
                }
            }

            for (int i = 0; i < header.length; i++) {
                sheet.autoSizeColumn(i);
            }

            os = new FileOutputStream(new File(OUT_FUEL_CONSUMPTION + ".xlsx"));
            book.write(os);
        } catch (ServiceException | IOException e) {
            System.out.println("all bad:(");
        } finally {
            close(os);
            close(book);
        }
    }

    private static void createDocumentProfit() {
        FileOutputStream os = null;
        Workbook book = null;
        try {
            List<Trip> trips = TripService.getAll();
            int rowNumber = 0;
            book = new XSSFWorkbook();
            createCellStyle(book);
            Sheet sheet = book.createSheet("Прибыль");
            Row row;
            Cell cell;
            Double driverProfit;
            Double expenses;
            Double cargoPrice;
            Double totalDriverProfit = 0.0;
            Double totalExpenses = 0.0;
            Double totalCargoPrice = 0.0;
            String[] header = {"id", "Оплата водителя", "Издержки", "Прибыль", " Итого", "Водитель", "Логист"};

            row = sheet.createRow(rowNumber++);
            for (int i = 0; i < header.length; i++) {
                cell = row.createCell(i);
                cell.setCellStyle(headerStyle);
                cell.setCellValue(header[i]);
            }

            for (Trip trip : trips) {
                if ("Завершён".equals(trip.getTripStatus())) {
                    row = sheet.createRow(rowNumber++);
                    driverProfit = trip.getDriverProfit().doubleValue();
                    expenses = trip.getExpenses().doubleValue();
                    cargoPrice = trip.getWaybill().getCargo().getPrice().doubleValue();
                    for (int cellNumber = 0; cellNumber < header.length; cellNumber++) {
                        cell = row.createCell(cellNumber);
                        cell.setCellStyle(defaultStyle);
                        if (cellNumber == 0) {
                            cell.setCellValue(trip.getId());
                        } else if (cellNumber == 1) {
                            cell.setCellValue(driverProfit);
                        } else if (cellNumber == 2) {
                            cell.setCellValue(expenses);
                        } else if (cellNumber == 3) {
                            cell.setCellValue(cargoPrice);
                        } else if (cellNumber == 4) {
                            if ((cargoPrice - driverProfit - expenses) < 0) {
                                cell.setCellStyle(redStyle);
                            } else {
                                cell.setCellStyle(greenStyle);
                            }
                            cell.setCellValue(cargoPrice - driverProfit - expenses);
                        } else if (cellNumber == 5) {
                            cell.setCellValue(trip.getDriver().getInitials());
                        } else if (cellNumber == 6) {
                            cell.setCellValue(trip.getRoute().getOwner().getInitials());
                        }
                    }
                    totalCargoPrice += cargoPrice;
                    totalDriverProfit += driverProfit;
                    totalExpenses += expenses;
                }
            }

            List<Object> footer = new ArrayList<>();
            footer.add("Итого");
            footer.add(totalDriverProfit);
            footer.add(totalExpenses);
            footer.add(totalCargoPrice);
            footer.add(totalCargoPrice - totalDriverProfit - totalExpenses);
            row = sheet.createRow(rowNumber);
            for (int i = 0; i < footer.size(); i++) {
                cell = row.createCell(i);
                cell.setCellStyle(bottomStyle);
                if (footer.get(i) instanceof Double) {
                    cell.setCellValue((Double) footer.get(i));
                } else if (footer.get(i) instanceof String) {
                    cell.setCellValue((String) footer.get(i));
                }
                if (i == 4) {
                    if ((Double) footer.get(i) < 0) {
                        cell.setCellStyle(redStyle);
                    } else {
                        cell.setCellStyle(greenStyle);
                    }
                }
            }

            for (int i = 0; i < header.length; i++) {
                sheet.autoSizeColumn(i);
            }

            os = new FileOutputStream(new File(OUT_PROFIT + ".xlsx"));
            book.write(os);
        } catch (ServiceException|IOException e) {
            System.out.println("all bad:(");
        } finally {
            close(os);
            close(book);
        }
    }

    private static void createDocumentRoute(Integer id) {
        FileOutputStream os = null;
        Workbook book = null;
        try {
            List<CheckPoint> route = RouteService.getByRouteId(id);
            int rowNumber = 0;
            book = new XSSFWorkbook();
            createCellStyle(book);
            Sheet sheet = book.createSheet("Маршрут");
            Row row;
            Cell cell;
            String[] header = {"id", "Название", "Тип", "X", " Y"};

            row = sheet.createRow(rowNumber++);
            for (int i = 0; i < header.length; i++) {
                cell = row.createCell(i);
                cell.setCellStyle(headerStyle);
                cell.setCellValue(header[i]);
            }
            for (CheckPoint checkPoint: route) {
                row = sheet.createRow(rowNumber++);
                for (int cellNumber = 0; cellNumber < header.length; cellNumber++) {
                    cell = row.createCell(cellNumber);
                    cell.setCellStyle(defaultStyle);
                    if (cellNumber == 0) {
                        cell.setCellValue(checkPoint.getId());
                    } else if (cellNumber == 1) {
                        cell.setCellValue(checkPoint.getName());
                    } else if (cellNumber == 2) {
                        cell.setCellValue(checkPoint.getPointType());
                    } else if (cellNumber == 3) {
                        cell.setCellValue(checkPoint.getX());
                    } else if (cellNumber == 4) {
                        cell.setCellValue(checkPoint.getY());
                    }
                }
            }
            for (int i = 0; i < header.length; i++) {
                sheet.autoSizeColumn(i);
            }

            os = new FileOutputStream(new File(OUT_ROUTE + ".xlsx"));
            book.write(os);

        } catch (ServiceException|IOException e) {
            System.out.println("all bad:(");
        } finally {
            close(os);
            close(book);
        }
    }

    private static void createDocumentWaybill(Integer id) {
        FileOutputStream os = null;
        Workbook book = null;
        int rowNumber = 0;
        try {
            Waybill waybill = WaybillService.getById(id);
            Cargo cargo = waybill.getCargo();
            List<String> cellName = new ArrayList<>();
            List<Object> cellData = new ArrayList<>();
            cellName.add("Наклодная"); cellData.add(" ");
            cellName.add("Идентификатор"); cellData.add(waybill.getId());
            cellName.add("Адрес точки назначения"); cellData.add(waybill.getDeliveryPoint().getAddress());
            cellName.add("Адрес отправителя"); cellData.add(waybill.getReceiver().getAddress());
            cellName.add("Груз:"); cellData.add(" ");
            cellName.add("Наименование"); cellData.add(cargo.getName());
            cellName.add("Тип"); cellData.add(cargo.getCargoType());
            cellName.add("Объём"); cellData.add(cargo.getCargoType());
            cellName.add("Вес"); cellData.add(cargo.getWeight());
            cellName.add("Поставщик"); cellData.add(cargo.getProvider().getName()+" "+cargo.getProvider().getAddress());
            cellName.add("Стоимость"); cellData.add(cargo.getPrice().doubleValue());
            book = new XSSFWorkbook();
            createCellStyle(book);
            Sheet sheet = book.createSheet("Waybill");
            Row row;
            Cell cell;

            for (int i = 0; i < cellName.size(); i++) {
                row = sheet.createRow(rowNumber++);
                cell = row.createCell(0);
                cell.setCellValue(cellName.get(i));
                cell.setCellStyle(headerStyle);
                cell = row.createCell(1);
                cell.setCellStyle(defaultStyle);
                if (cellData.get(i) instanceof Double) {
                    cell.setCellValue((Double) cellData.get(i));
                } else if (cellData.get(i) instanceof String) {
                    cell.setCellValue((String) cellData.get(i));
                } else if (cellData.get(i) instanceof Integer) {
                    cell.setCellValue((Integer) cellData.get(i));
                }
                if (i == 0 || i == 4) {
                    cell.setCellStyle(whiteStyle);
                }
            }

            for (int i = 0; i < 2; i++) {
                sheet.autoSizeColumn(i);
            }

            os = new FileOutputStream(new File(OUT_WAYBILL + ".xlsx"));
            book.write(os);
        } catch (ServiceException e) {
            System.out.println("Can not find waybill");
        } catch (IOException e) {
            System.out.println("don't work with file");
        } finally {
            close(os);
            close(book);
        }
    }

    private static void createDocumentStatusTrip() {
        FileOutputStream os = null;
        Workbook book = null;
        int rowNumber = 0;
        try {
            List<Trip> trips = TripService.getAll();
            book = new XSSFWorkbook();
            Sheet sheet = book.createSheet("Статус грузоперевозок");
            Row row;
            Cell cell;
            DataFormat format = book.createDataFormat();
            CellStyle dateStyle = book.createCellStyle();
            dateStyle.setDataFormat(format.getFormat("dd.MM.yyyy"));

            String[] names = {"id", "Статус", "Дата выезда", "Время приезда", "Реальное время приезда", "Водитель"};

            row = sheet.createRow(rowNumber++);
            for (int i = 0; i < names.length; i++) {
                cell = row.createCell(i);
                cell.setCellValue(names[i]);
            }
            for (Trip trip: trips) {
                row = sheet.createRow(rowNumber++);
                for (int cellNumber = 0; cellNumber < names.length; cellNumber++) {
                    cell = row.createCell(cellNumber);
                    if (cellNumber == 0) {
                        cell.setCellValue(trip.getId());
                    } else if (cellNumber == 1) {
                        cell.setCellValue(trip.getTripStatus());
                    } else if (cellNumber == 2) {
                        cell.setCellStyle(dateStyle);
                        cell.setCellValue(trip.getStartDate());
                    } else  if (cellNumber == 3) {
                        cell.setCellStyle(dateStyle);
                        cell.setCellValue(trip.getExpectedFinishDate());
                    } else  if (cellNumber == 4) {
                        cell.setCellStyle(dateStyle);
                        cell.setCellValue(trip.getRealFinishDate());
                    } else if (cellNumber == 5) {
                        cell.setCellValue(trip.getDriver().getInitials());
                    }
                }
            }
            for (int i = 0; i < names.length; i++) {
                sheet.autoSizeColumn(i);
            }
            os = new FileOutputStream(new File(OUT_STATUS_TRIP + ".xlsx"));
            book.write(os);

        } catch (ServiceException e) {
            System.out.println("Don't get all trip");
        } catch (IOException e) {
            System.out.println("all bad:(");
        } finally {
            close(os);
            close(book);
        }
    }

    private static String saveCSV(String inFile) {
        String outFile = inFile + ".csv";
        File inputFile = new File(inFile + ".xlsx");
        File outputFile = new File(outFile);
        FileOutputStream fos = null;
        OutputStreamWriter out = null;
        XSSFWorkbook wBook = null;

        StringBuffer data = new StringBuffer();
        try {
            fos = new FileOutputStream(outputFile);
            wBook = new XSSFWorkbook(new FileInputStream(inputFile));

            XSSFSheet sheet = wBook.getSheetAt(0);
            Row row;
            Cell cell;
            for (Row aSheet : sheet) {
                row = aSheet;

                Iterator<Cell> cellIterator = row.cellIterator();
                while (cellIterator.hasNext()) {
                    cell = cellIterator.next();
                    switch (cell.getCellType()) {
                        case Cell.CELL_TYPE_BOOLEAN:
                            data.append(cell.getBooleanCellValue() + ";");

                            break;
                        case Cell.CELL_TYPE_NUMERIC:
                            data.append(cell.getNumericCellValue() + ";");

                            break;
                        case Cell.CELL_TYPE_STRING:
                            data.append(cell.getStringCellValue() + ";");
                            break;
                        default:
                            data.append(cell + ";");
                    }
                }
                data.append("\r\n");
            }
            out = new OutputStreamWriter(fos, "Windows-1251");
            //out = new OutputStreamWriter(fos, "Unicode");
            out.write(data.toString());
            out.flush();
        } catch (IOException e) {
            System.out.println("Всё плохо:(");
        } finally {
            close(fos);
            close(out);
            close(wBook);
        }
        return outFile;
    }

    private static String savePDF(String inFile) {
        String outFile = inFile + ".pdf";
        FileInputStream inputDocument = null;
        XSSFWorkbook wBook = null;
        Font font;
        try {
            BaseFont bf = BaseFont.createFont("/Library/Fonts/Arial.ttf", BaseFont.IDENTITY_H, BaseFont.EMBEDDED);
            font = new Font(bf);
            inputDocument = new FileInputStream(new File(inFile + ".xlsx"));
            wBook = new XSSFWorkbook(inputDocument);
            XSSFSheet wSheet = wBook.getSheetAt(0);
            Iterator<Row> rowIterator = wSheet.iterator();
            Document pdfDoc = new Document();
            PdfWriter.getInstance(pdfDoc, new FileOutputStream(outFile));
            pdfDoc.open();
            PdfPTable table = null;

            PdfPCell tableCell;
            while (rowIterator.hasNext()) {
                Row row = rowIterator.next();
                Iterator<Cell> cellIterator = row.iterator();
                while (cellIterator.hasNext()) {
                    if (table == null) {
                        table = new PdfPTable(iteratorSize(cellIterator));
                        cellIterator = row.iterator();
                    }
                    Cell cell = cellIterator.next();
                    switch (cell.getCellType()) {
                        case Cell.CELL_TYPE_BOOLEAN:
                            
                            tableCell = new PdfPCell( new Phrase(String.valueOf(cell.getBooleanCellValue())));
                            table.addCell(tableCell);
                        case Cell.CELL_TYPE_STRING:
                            table.addCell(new Paragraph(cell.getStringCellValue(), font));
                            break;

                        case Cell.CELL_TYPE_NUMERIC:
                            if (cell.getNumericCellValue() > 42000) {
                                SimpleDateFormat sf = new SimpleDateFormat("dd.MM.yyyy");
                                String formattedDate = sf.format(cell.getDateCellValue());
                                tableCell = new PdfPCell(new Phrase(formattedDate));
                                table.addCell(tableCell);
                            } else {
                                table.addCell(String.valueOf(cell.getNumericCellValue()));
                            }
                            break;

                        default:
                            tableCell = new PdfPCell(new Phrase(" "));
                            table.addCell(tableCell);
                    }
                }
            }
            pdfDoc.add(table);
            pdfDoc.close();
        } catch (IOException | DocumentException e) {
            e.printStackTrace();
        } finally {
            close(inputDocument);
            close(wBook);
        }
        return outFile;
    }

    private static void close(Closeable closeable) {
        if (closeable != null) {
            try {
              closeable.close();
            } catch (IOException e) {
                System.out.println("Can't close stream!");
            }
        }
    }

    private static int iteratorSize(Iterator iterator) {
        int i = 0;
        for ( ; iterator.hasNext() ; ++i ) iterator.next();
        return i;
    }
}
