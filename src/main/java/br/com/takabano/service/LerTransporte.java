//package br.com.takabano.service;
//
//import java.io.IOException;
//import java.io.InputStream;
//import java.util.Iterator;
//import java.util.List;
//
//import org.apache.poi.ss.usermodel.Cell;
//import org.apache.poi.ss.usermodel.Row;
//import org.apache.poi.ss.usermodel.Sheet;
//import org.apache.poi.ss.usermodel.Workbook;
//import org.apache.poi.xssf.usermodel.XSSFWorkbook;
//
//import br.com.takabano.entity.Motorista;
//import br.com.takabano.entity.Transporte;
//import br.com.takabano.helper.VerificaObjeto;
//public class LerTransporte implements Runnable {
//	
//	static String[] HEADERs = { "caminhao", "tipoCarga", "transportadora", "qtdItensCarga", "valorUn", "origem", "destino", "valorTotalCarga", "vlrFrete", "motorista_id" };
//	static String SHEET = "Transporte";
//
//	private List<Transporte> transportes;
//	private InputStream is;
//	private List<Motorista> motoristas;
//
//	public LerTransporte(List<Transporte> transportes, InputStream is, List<Motorista> motorista) {
//		this.transportes = transportes;
//		this.is = is;
//		this.motoristas = motorista;
//	}
//
//	@Override
//	public void run() {
//		try {
//			VerificaObjeto verificaObjeto = new VerificaObjeto();
//			Workbook workbook = new XSSFWorkbook(is);
//
//			Sheet sheet = workbook.getSheet(SHEET);
//			Iterator<Row> rows = sheet.iterator();
//
//
//			int rowNumber = 0;
//			while (rows.hasNext()) {
//				Row currentRow = rows.next();
//
//				// skip header
//				if (rowNumber == 0) {
//					rowNumber++;
//					continue;
//				}
//
//				Iterator<Cell> cellsInRow = currentRow.iterator();
//
//				Transporte transporte = new Transporte();
//
//				int cellIdx = 0;
//				while (cellsInRow.hasNext()) {
//					Cell currentCell = cellsInRow.next();
//
//					switch (cellIdx) {
//					case 0:
//						transporte.setCaminhao(currentCell.getStringCellValue());
//						break;
//					case 1:
//						transporte.setTipoCarga(currentCell.getStringCellValue());
//						break;
//					case 2:
//						transporte.setTransportadora(currentCell.getStringCellValue());
//						break;
//					case 3:
//						transporte.setQtdItensCarga(currentCell.getNumericCellValue());
//						break;
//					case 4:
//						transporte.setValorUn(currentCell.getNumericCellValue());
//						break;
//					case 5:
//						transporte.setOrigem(currentCell.getStringCellValue());
//						break;
//					case 6:
//						transporte.setDestino(currentCell.getStringCellValue());
//						break;
//					case 7:
//						transporte.setVlrTotalCarga(currentCell.getNumericCellValue());
//						break;
//					case 8:
//						transporte.setVlrFrete(currentCell.getNumericCellValue());
//						break;
//					case 9:
//						for (Motorista item : motoristas) {
//							if(currentCell.getNumericCellValue() == item.getId()) {
//								transporte.setMotorista(item);								
//							}
//						}
//						break;
//
//					default:
//						break;
//					}
//					cellIdx++;
//				}
//				if(verificaObjeto.verificaPlanilha(transporte)) {
//					transportes.add(transporte);	
//				}
//			}
//			workbook.close();
//		} catch (IOException e) {
//			throw new RuntimeException("fail to parse Excel file: " + e.getMessage());
//		}
//
//	}
//
//}
