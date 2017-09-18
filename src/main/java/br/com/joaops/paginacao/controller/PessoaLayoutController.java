/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.joaops.paginacao.controller;

import br.com.joaops.paginacao.Main;
import br.com.joaops.paginacao.dto.PessoaDto;
import br.com.joaops.paginacao.service.PessoaService;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.Pagination;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.util.Callback;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;

/**
 * FXML Controller class
 *
 * @author João Paulo
 */
@Controller
public class PessoaLayoutController implements Initializable {
    
    private Main main;
    
    @FXML
    private TextField textFieldNome;
    
    @FXML
    private DatePicker datePickerNascimento;
    
    @FXML
    private TableView<PessoaDto> tableViewPessoa;
    
    @FXML
    private TableColumn<PessoaDto, Long> tableColumnId;
    
    @FXML
    private TableColumn<PessoaDto, String> tableColumnNome;
    
    @FXML
    private TableColumn<PessoaDto, Date> tableColumnNascimento;
    
    @FXML
    private Pagination pagination;
    
    @Autowired
    private PessoaService pessoaService;
    
    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        updateLayout();
        tableViewPessoa.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null) {
                textFieldNome.setText(newValue.getNome());
                datePickerNascimento.setValue(newValue.getNascimento().toInstant().atZone(ZoneId.systemDefault()).toLocalDate());
            }
        });
    }
    
    public void setMain(Main main) {
        this.main = main;
    }
    
    public void updateLayout() {
        int size = 10;
        Pageable p = new PageRequest(0, size);
        Page<PessoaDto> pessoas = pessoaService.findAll(p);
        pagination.setPageCount(pessoas.getTotalPages());
        pagination.setPageFactory(new Callback<Integer, Node>() {
            @Override
            public Node call(Integer param) {
                Page<PessoaDto> pessoas = pessoaService.findAll(new PageRequest(param, size));
                loadTableView(pessoas.getContent());
                return new Label("");
            }
        });
    }
    
    public void loadTableView(List<PessoaDto> pessoas) {
        tableColumnId.setCellValueFactory(new PropertyValueFactory<>("id"));
        tableColumnNome.setCellValueFactory(new PropertyValueFactory<>("nome"));
        tableColumnNascimento.setCellValueFactory(new PropertyValueFactory<>("nascimento"));
        tableColumnNascimento.setCellFactory((TableColumn<PessoaDto, Date> column) -> {
            return new TableCell<PessoaDto, Date>() {
                @Override
                protected void updateItem(Date item, boolean empty) {
                    super.updateItem(item, empty);
                    if (item == null || empty) {
                        setText(null);
                    } else {
                        setText(new SimpleDateFormat("dd/MM/yyyy").format(item));
                    }
                }
            };
        });
        ObservableList<PessoaDto> observableList = FXCollections.observableArrayList(pessoas);
        tableViewPessoa.setItems(observableList);
    }
    
    @FXML
    public void create() {
        PessoaDto pessoaDto = new PessoaDto();
        pessoaDto.setNome(textFieldNome.getText());
        LocalDate ld = datePickerNascimento.getValue();
        Calendar c =  Calendar.getInstance();
        c.set(ld.getYear(), ld.getMonthValue(), ld.getDayOfMonth());
        pessoaDto.setNascimento(c.getTime());
        pessoaService.save(pessoaDto);
        tableViewPessoa.getSelectionModel().clearSelection();
        textFieldNome.setText("");
        datePickerNascimento.setValue(null);
        updateLayout();
    }
    
    @FXML
    public void update() {
        PessoaDto pessoaDto = tableViewPessoa.getSelectionModel().getSelectedItem();
        if (pessoaDto == null) return;
        pessoaDto.setNome(textFieldNome.getText());
        LocalDate ld = datePickerNascimento.getValue();
        Calendar c =  Calendar.getInstance();
        c.set(ld.getYear(), ld.getMonthValue(), ld.getDayOfMonth());
        pessoaDto.setNascimento(c.getTime());
        pessoaService.save(pessoaDto);
        textFieldNome.setText("");
        datePickerNascimento.setValue(null);
        updateLayout();
    }
    
    @FXML
    public void delete() {
        PessoaDto pessoaDto = tableViewPessoa.getSelectionModel().getSelectedItem();
        if (pessoaDto == null) return;
        pessoaService.delete(pessoaDto);
        textFieldNome.setText("");
        datePickerNascimento.setValue(null);
        updateLayout();
    }
    
}