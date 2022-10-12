package br.com.takabano.helper;

import java.io.IOException;
import java.io.InputStream;
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
import br.com.takabano.repository.MotoristaRepository;
@Component
public class ClienteHelper {

	public static String TYPE = "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet";
	static String[] HEADERs = { "nome, numDocumento" };
	static String SHEET = "Cliente";
	@Autowired
	MotoristaRepository repository;

	public static boolean hasExcelFormat(MultipartFile file) {

		if (!TYPE.equals(file.getContentType())) {
			return false;
		}

		return true;
	}

	public List<Cliente> excelToCliente(InputStream is) {		
		List<Cliente> clientes = new ArrayList<>();
		lerPlanilha(clientes,is);
		return clientes;
	}
	
	private void lerPlanilha(List<Cliente> clientes, InputStream is) {
		try {
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
				Cliente cliente = new Cliente();
				int cellIdx = 0;
				while (cellsInRow.hasNext()) {
					Cell currentCell = cellsInRow.next();
					switch (cellIdx) {
					case 0:
						cliente.setNome(currentCell.getStringCellValue());
						break;
					case 1:
						Long numDocumento = (long) currentCell.getNumericCellValue();
						cliente.setNumDocumento(numDocumento.toString());
						break;
					default:
						break;
					}
					cellIdx++;
				}
				clientes.add(cliente);	
				
			}
			workbook.close();
		} catch (IOException e) {
			throw new RuntimeException("fail to parse Excel file: " + e.getMessage());
		}

	}
}