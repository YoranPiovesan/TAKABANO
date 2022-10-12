package br.com.takabano.helper;

import java.io.IOException;
import java.io.InputStream;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import br.com.takabano.entity.Cliente;
import br.com.takabano.entity.Motorista;
import br.com.takabano.entity.Transporte;
import br.com.takabano.repository.ClienteRepository;
import br.com.takabano.repository.MotoristaRepository;
@Component
public class TransporteHelper {

	public static String TYPE = "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet";
	static String[] HEADERs = { "caminhao", "tipoCarga", "transportadora", "qtdItensCarga", "valorUn", "origem", "destino", "valorTotalCarga", "vlrFrete", "motorista_id", "previsaoEntrega", "cliente" };
	static String SHEET = "Transporte";
	@Autowired
	MotoristaRepository motoristaRepository;
	@Autowired
	ClienteRepository clienteRepository;

	public static boolean hasExcelFormat(MultipartFile file) {

		if (!TYPE.equals(file.getContentType())) {
			return false;
		}

		return true;
	}

	public List<Transporte> excelToTransporte(InputStream is) {
		
		List<Transporte> transportes = new ArrayList<Transporte>();
		lerPlanilha(transportes,is);
		return transportes;
	}
	
	private void lerPlanilha(List<Transporte> transportes, InputStream is) {
		try {
			VerificaObjeto verificaObjeto = new VerificaObjeto();
			Workbook workbook = new XSSFWorkbook(is);

			Sheet sheet = workbook.getSheet(SHEET);
			Iterator<Row> rows = sheet.iterator();

			int rowNumber = 0;
			while (rows.hasNext()) {
				Row currentRow = rows.next();
				
				if (rowNumber == 0) {
					rowNumber++;
					continue;
				}

				Iterator<Cell> cellsInRow = currentRow.iterator();

				Transporte transporte = new Transporte();

				int cellIdx = 0;
				while (cellsInRow.hasNext()) {
					Cell currentCell = cellsInRow.next();

					switch (cellIdx) {
					case 0:
						transporte.setCaminhao(currentCell.getStringCellValue());
						break;
					case 1:
						transporte.setTipoCarga(currentCell.getStringCellValue());
						break;
					case 2:
						transporte.setTransportadora(currentCell.getStringCellValue());
						break;
					case 3:
						transporte.setQtdItensCarga(currentCell.getNumericCellValue());
						break;
					case 4:
						transporte.setValorUn(currentCell.getNumericCellValue());
						break;
					case 5:
						transporte.setOrigem(currentCell.getStringCellValue());
						break;
					case 6:
						transporte.setDestino(currentCell.getStringCellValue());
						break;
					case 7:
						transporte.setVlrTotalCarga(currentCell.getNumericCellValue());
						break;
					case 8:
						transporte.setVlrFrete(currentCell.getNumericCellValue());
						break;
					case 9:
						Long valorMotorista = (long) currentCell.getNumericCellValue();  
						Motorista motorista = motoristaRepository.findById(valorMotorista).get();
						transporte.setMotorista(motorista);
					case 10:
						LocalDate data = currentCell.getLocalDateTimeCellValue().toLocalDate();  
						transporte.setPrevisaoEntrega(data);
						break;
					case 11:
						Long valorCliente = (long) currentCell.getNumericCellValue();  
						Cliente cliente = clienteRepository.findById(valorCliente).get();
						transporte.setCliente(cliente);
						break;

					default:
						break;
					}
					cellIdx++;
				}
				if(verificaObjeto.verificaPlanilha(transporte)) {
					transportes.add(transporte);	
				}
			}
			workbook.close();
		} catch (IOException e) {
			throw new RuntimeException("fail to parse Excel file: " + e.getMessage());
		}

	}
}