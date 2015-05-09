/***********************************************************************************
 * 
 * Copyright (c) 2015 Kamil Baczkowicz
 * 
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 * 
 *    Kamil Baczkowicz - initial API and implementation and/or initial documentation
 *    
 */
package pl.baczkowicz.mqttspy.ui;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TitledPane;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeTableCell;
import javafx.scene.control.TreeTableColumn;
import javafx.scene.control.TreeTableView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.util.Callback;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import pl.baczkowicz.mqttspy.configuration.ConfigurationManager;
import pl.baczkowicz.mqttspy.connectivity.MqttAsyncConnection;
import pl.baczkowicz.mqttspy.events.EventManager;
import pl.baczkowicz.mqttspy.scripts.InteractiveScriptManager;
import pl.baczkowicz.mqttspy.testcases.TestCase;
import pl.baczkowicz.mqttspy.testcases.TestCaseManager;
import pl.baczkowicz.mqttspy.testcases.TestCaseStatus;
import pl.baczkowicz.mqttspy.ui.panes.TitledPaneController;
import pl.baczkowicz.mqttspy.ui.properties.TestCaseProperties;

/**
 * Controller for the test cases execution window.
 */
public class TestCasesExecutionController extends AnchorPane implements Initializable, TitledPaneController
{
	/** Initial and minimal scene/stage width. */	
	public final static int WIDTH = 780;
	
	/** Initial and minimal scene/stage height. */
	public final static int HEIGHT = 550;
		
	final static Logger logger = LoggerFactory.getLogger(TestCasesExecutionController.class);	

	private TitledPane pane;
	
	@FXML
	private TreeTableView<TestCaseProperties> scriptTree;
	
	private TreeItem<TestCaseProperties> root = new TreeItem<>();
	
	/**
	 * The name of this field needs to be set to the name of the pane +
	 * Controller (i.e. <fx:id>Controller).
	 */
	@FXML
	private TestCaseExecutionController testCaseExecutionPaneController;
	
	@FXML
	private TreeTableColumn<TestCaseProperties, String> nameColumn;	
	
	@FXML
	private TreeTableColumn<TestCaseProperties, String> lastRunColumn;
	
	@FXML
	private TreeTableColumn<TestCaseProperties, TestCaseStatus> statusColumn;
	
	@FXML
	private ContextMenu scriptTreeContextMenu;	

	@FXML
	private MenuItem setLocationMenu;

	@FXML
	private MenuItem enqueueAllMenu;

	@FXML
	private MenuItem enqueueSelectedMenu;

	@FXML
	private MenuItem enqueueNotRunMenu;

	@FXML
	private MenuItem enqueueFailedMenu;
	
	@FXML
	private MenuItem clearEnqueuedMenu;
	
	@FXML
	private Label enqueuedLabel;
	
	private String scriptLocation;
	
	private EventManager eventManager;

	private ConfigurationManager configurationManager;
	
	private InteractiveScriptManager scriptManager;

	private TestCaseManager testCaseManager;

	private MqttAsyncConnection connection;
	
	public void initialize(URL location, ResourceBundle resources)
	{
		scriptManager = new InteractiveScriptManager(eventManager, null);
		testCaseManager = new TestCaseManager(scriptManager, testCaseExecutionPaneController);
		
		// Set location
		setLocationMenu.setOnAction(new EventHandler<ActionEvent>()
		{		
			@Override
			public void handle(ActionEvent event)
			{
				// TODO: select location
				scriptLocation = "/home/kamil/Programming/Git/mqtt-spy/src/test/resources/test_cases";
				
				root.getChildren().clear();
				for (final TestCaseProperties properties : testCaseManager.loadTestCases(scriptLocation))
				{
					root.getChildren().add(new TreeItem<TestCaseProperties>(properties));
				}				
				
				scriptTree.getSelectionModel().clearSelection();
				updateContextMenu();
				// TODO: get all dirs
				// TODO: load
			}
		});
		
		enqueueAllMenu.setOnAction(new EventHandler<ActionEvent>()
		{		
			@Override
			public void handle(ActionEvent event)
			{
				testCaseManager.enqueueAllTestCases();
				// TODO: start
				final TreeItem<TestCaseProperties> selected = scriptTree.getSelectionModel().getSelectedItem();
			
				if (selected != null && selected.getValue() != null)
				{
					final TestCaseProperties testCaseProperties = selected.getValue();
					
					testCaseManager.runTestCase(testCaseProperties);
				}
			}
		});		
		
		enqueueSelectedMenu.setOnAction(new EventHandler<ActionEvent>()
		{		
			@Override
			public void handle(ActionEvent event)
			{
				final TreeItem<TestCaseProperties> selected = scriptTree.getSelectionModel().getSelectedItem();
			
				if (selected != null && selected.getValue() != null)
				{
					final TestCaseProperties testCaseProperties = selected.getValue();
					
					testCaseManager.enqueueTestCase(testCaseProperties);
				}
			}
		});
		
		enqueueNotRunMenu.setOnAction(new EventHandler<ActionEvent>()
		{		
			@Override
			public void handle(ActionEvent event)
			{
				testCaseManager.enqueueAllNotRun();
			}
		});
		
		enqueueFailedMenu.setOnAction(new EventHandler<ActionEvent>()
		{		
			@Override
			public void handle(ActionEvent event)
			{
				testCaseManager.enqueueAllFailed();
			}
		});
		
		clearEnqueuedMenu.setOnAction(new EventHandler<ActionEvent>()
		{			
			@Override
			public void handle(ActionEvent event)
			{
				testCaseManager.clearEnqueued();				
			}
		});
						
		scriptTree.setRoot(root);
		scriptTree.setShowRoot(false);
		
		scriptTree.setOnMouseClicked(new EventHandler<MouseEvent>()
		{
			@Override
			public void handle(MouseEvent event)
			{
				showSelected();			
				updateContextMenu();
			}
		});
		
		nameColumn.setCellValueFactory
		(
	            (TreeTableColumn.CellDataFeatures<TestCaseProperties, String> param) -> 
	            new ReadOnlyStringWrapper(param.getValue().getValue().getName())
	    );
		
		lastRunColumn.setCellValueFactory
		(
	            (TreeTableColumn.CellDataFeatures<TestCaseProperties, String> p) -> 
	            new ReadOnlyStringWrapper(p.getValue().getValue().lastStartedProperty().getValue())
	    );
		
//		statusColumn.setCellValueFactory
//		(
//	            (TreeTableColumn.CellDataFeatures<TestCaseProperties, TestCaseStatus> param) -> 
//	            new ReadOnlyObjectWrapper<TestCaseStatus>(param.getValue().getValue().statusProperty().getValue())
//	    );
		
		statusColumn.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<TestCaseProperties, TestCaseStatus>, ObservableValue<TestCaseStatus>>() 
		{
            @Override public ObservableValue<TestCaseStatus> call(TreeTableColumn.CellDataFeatures<TestCaseProperties, TestCaseStatus> p) 
            {
                return p.getValue().getValue().statusProperty();
            }
        });
		
		statusColumn.setCellFactory(new Callback<TreeTableColumn<TestCaseProperties,TestCaseStatus>, TreeTableCell<TestCaseProperties,TestCaseStatus>>()
		{			
			public TreeTableCell<TestCaseProperties, TestCaseStatus> call(
					TreeTableColumn<TestCaseProperties, TestCaseStatus> param)
			{
				final TreeTableCell<TestCaseProperties, TestCaseStatus> cell = new TreeTableCell<TestCaseProperties, TestCaseStatus>()
				{
					@Override
					public void updateItem(TestCaseStatus item, boolean empty)
					{
						super.updateItem(item, empty);
						if (!isEmpty())
						{
							setGraphic(testCaseExecutionPaneController.getIconForStatus(item));
						}
						else
						{
							setGraphic(null);
						}
					}
				};
				cell.setAlignment(Pos.CENTER);
				cell.setPadding(new Insets(0, 0, 0, 0));
				
				return cell;
			}
		});
		
		// Note: important - without that, cell height goes nuts with progress indicator
		scriptTree.setFixedCellSize(24);		
		
		testCaseExecutionPaneController.setTestCaseManager(testCaseManager);
	}	

	public void init()
	{
		//
	}	
	
	public void updateContextMenu()
	{
		enqueueAllMenu.setDisable(true);
		enqueueSelectedMenu.setDisable(true);
		enqueueNotRunMenu.setDisable(true);
		enqueueFailedMenu.setDisable(true);
		clearEnqueuedMenu.setDisable(testCaseManager.getEnqueuedCount() == 0 ? true : false);
		
		if (root.getChildren().size() > 0)
		{		
			enqueueAllMenu.setDisable(false);
			
			final TreeItem<TestCaseProperties> selected = scriptTree.getSelectionModel().getSelectedItem();

			if (selected != null && selected.getValue() != null)
			{
				enqueueSelectedMenu.setDisable(false);
			}
			
			// TODO: check if any are failed/not run
			enqueueNotRunMenu.setDisable(false);
			enqueueFailedMenu.setDisable(false);									
		}
	}
	
	public void updateLabels()
	{
		// TODO: update other labels
		enqueuedLabel.setText("Enqueued: " + testCaseManager.getEnqueuedCount());
	}
	
	public void showSelected()
	{
		final TreeItem<TestCaseProperties> selected = scriptTree.getSelectionModel().getSelectedItem();

		//logger.info("About to display selected test case");
		if (selected != null && selected.getValue() != null)
		{					
			final TestCaseProperties testCaseProperties = selected.getValue();
			logger.info("About to display selected test case - " + testCaseProperties.getName());
			testCaseExecutionPaneController.display(testCaseProperties, ((TestCase) testCaseProperties.getScript()).getSteps());
		}
		else
		{
			logger.warn("No test case selected");
		}
	}
	
	
	// ===============================
	// === Setters and getters =======
	// ===============================
	
	public void setEventManager(final EventManager eventManager)
	{
		this.eventManager = eventManager;
	}

	public void setConfingurationManager(final ConfigurationManager configurationManager)
	{
		this.configurationManager = configurationManager;
	}
	
	@Override
	public TitledPane getTitledPane()
	{
		return pane;
	}

	@Override
	public void setTitledPane(TitledPane pane)
	{
		this.pane = pane;
	}

	public void setConnection(MqttAsyncConnection connection)
	{
		this.connection = connection;		
	}
}