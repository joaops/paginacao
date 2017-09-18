package br.com.joaops.paginacao.util;

import java.io.PrintWriter;
import java.io.StringWriter;
import javafx.concurrent.Service;
import javafx.concurrent.Task;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import org.springframework.stereotype.Component;

/**
 *
 * @author João Paulo
 */
@Component
public class ControlsUtil {
    
    public void alertException(String title, String header, String content, Exception e) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(title);
        alert.setHeaderText(header);
        alert.setContentText(content);
        // Create expandable Exception.
        StringWriter sw = new StringWriter();
        PrintWriter pw = new PrintWriter(sw);
        e.printStackTrace(pw);
        String exceptionText = sw.toString();
        Label label = new Label("Rastreamento de pilha de exceção:");
        TextArea textArea = new TextArea(exceptionText);
        textArea.setEditable(false);
        textArea.setWrapText(true);
        textArea.setMaxWidth(Double.MAX_VALUE);
        textArea.setMaxHeight(Double.MAX_VALUE);
        GridPane.setVgrow(textArea, Priority.ALWAYS);
        GridPane.setHgrow(textArea, Priority.ALWAYS);
        GridPane expContent = new GridPane();
        expContent.setMaxWidth(Double.MAX_VALUE);
        expContent.add(label, 0, 0);
        expContent.add(textArea, 0, 1);
        // Set expandable Exception into the dialog pane.
        alert.getDialogPane().setExpandableContent(expContent);
        alert.showAndWait();
    }
    
    public void showTempLabel(Label label, String startMessage, String endMessage, int time) {
        label.setText(startMessage);
        Service service = new Service() {
            @Override
            protected Task createTask() {
                return new Task<Void>() {
                @Override
                protected Void call() throws Exception {
                    Thread.sleep(time);
                    return null;
                }
            };
            }
        };
        if(!service.isRunning()) {
            service.start();
        }
        service.setOnSucceeded(e -> {
            label.setText(endMessage);
            service.reset();
        });
    }
    
}