package appGUI;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Map;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextField;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class appFrame extends JFrame {
	
	Connection conn=null;
	PreparedStatement state=null;
	ResultSet result=null;
	
	int idClient=-1;
	int idCar=-1;
	int idRepair=-1;
	int idMechanic=-1;
	int idMechanicRepair=-1;
	
	JTabbedPane tab=new JTabbedPane();
	
	JPanel userPanel=new JPanel();
	
	JPanel userPanel1=new JPanel();
	JPanel userPanel2=new JPanel();
	JPanel userPanel3=new JPanel();
	
	JLabel userNameL=new JLabel("Име на клиент:");
	JLabel userPhoneL=new JLabel("Телефон:");
	JLabel userEmailL=new JLabel("Имейл:");
	
	JTextField userNameTF=new JTextField();
	JTextField userPhoneTF=new JTextField();
	JTextField userEmailTF=new JTextField();
	
	JButton userAdd=new JButton("Добави");
	JButton userDel=new JButton("Изтрий");
	JButton userEdit=new JButton("Редактирай");
	JButton userSearch=new JButton("Търсене по име");
	JButton userGiveAll=new JButton("Покажи всички хора");
	
	JTable userTable=new JTable();
	JScrollPane userScroll=new JScrollPane(userTable);
	
	JPanel carPanel=new JPanel();
	
	JPanel carPanel1=new JPanel();
	JPanel carPanel2=new JPanel();
	JPanel carPanel3=new JPanel();
	
	JLabel carVehicleNumberL=new JLabel("Регистрационен номер на автомобил:");
	JLabel carBrandL=new JLabel("Марка на автомобил:");
	JLabel carModelL=new JLabel("Модел на автомобил:");
	JLabel carProductionYearL=new JLabel("Година на производство:");
	JLabel carOwnerL=new JLabel("Собственик на автомобил:");
	
	JTextField carVehicleNumberTF=new JTextField();
	JTextField carBrandTF=new JTextField();
	JTextField carModelTF=new JTextField();
	JTextField carProductionYearTF=new JTextField();
	JComboBox<String> carOwnerCB=new JComboBox<>();
	//mapper for comboBox for every client and their id.
	Map<String, Integer> clientMap = new HashMap<>();
	
	JButton carAdd=new JButton("Добави");
	JButton carDel=new JButton("Изтрий");
	JButton carEdit=new JButton("Редактирай");
	JButton carSearch=new JButton("Търсене по номер");
	JButton carGiveAll=new JButton("Покажи всички коли");
	
	JTable carTable=new JTable();
	JScrollPane carScroll=new JScrollPane(carTable);
	
	JPanel repairPanel=new JPanel();
	
	JPanel repairPanel1=new JPanel();
	JPanel repairPanel2=new JPanel();
	JPanel repairPanel3=new JPanel();
	
	JLabel repairDesrciptionL=new JLabel("Описание на сервиза:");
	JLabel repairCostL=new JLabel("Цена(лв.):");
	JLabel repairDateL = new JLabel("Дата (гггг-мм-дд):");
	JLabel repairCarL=new JLabel("Избери кола:");
	
	JTextField repairDesrciptionTF=new JTextField();
	JTextField repairCostTF=new JTextField();
	JTextField repairDateTF = new JTextField();
	JComboBox<String> repairCarCB = new JComboBox<>();
	Map<String, Integer> carMap = new HashMap<>();
	
	JButton repairAdd=new JButton("Добави");
	JButton repairDel=new JButton("Изтрий");
	JButton repairEdit=new JButton("Редактирай");
	JButton repairSearch=new JButton("Търсене по кола");
	JButton repairGiveAll=new JButton("Покажи всички ремонти");
	
	JTable repairTable=new JTable();
	JScrollPane repairScroll=new JScrollPane(repairTable);
	
	JPanel mechanicPanel=new JPanel();
	
	JPanel mechanicPanel1=new JPanel();
	JPanel mechanicPanel2=new JPanel();
	JPanel mechanicPanel3=new JPanel();
	
	JLabel mechanicNameL=new JLabel("Механик:");
	JLabel mechanicSpecializationL=new JLabel("Специализация:");
	JLabel mechanicRepairL=new JLabel("Избери ремонт:");
	
	JTextField mechanicNameTF=new JTextField();
	JTextField mechanicSpecializationTF=new JTextField();

	JComboBox<String> mechanicRepairCB = new JComboBox<>();
	Map<String, Integer> repairMap = new HashMap<>();
	
	JButton mechanicAdd=new JButton("Добави");
	JButton mechanicDel=new JButton("Изтрий");
	JButton mechanicEdit=new JButton("Редактирай");
	JButton mechanicSearch=new JButton("Търсене по име");
	JButton mechanicGiveAll=new JButton("Покажи всички механици");
	
	JTable mechanicTable=new JTable();
	JScrollPane mechanicScroll=new JScrollPane(mechanicTable);
	
	JPanel referencePanel = new JPanel();
	
	JPanel referencePanel1=new JPanel();
	JPanel referencePanel2=new JPanel();
	JPanel referencePanel3=new JPanel();
	
	JLabel referenceMechanicNameL=new JLabel("Механик:");
	JLabel referenceStartDateRepairL=new JLabel("Начална дата(гггг-мм-дд):");
	JLabel referenceEndDateRepairL=new JLabel("Крайна дата(гггг-мм-дд):");
	
	JTextField referenceMechanicNameTF=new JTextField();
	JTextField referenceStartDateRepairTF=new JTextField();
	JTextField referenceEndDateRepairTF=new JTextField();
	
	JButton referenceInfoBtn=new JButton("Справка");
	
	JTable referenceTable=new JTable();
	JScrollPane referenceScroll=new JScrollPane(referenceTable);
	
	/**
	 * 
	 */
	public appFrame() {
		
		conn=MyDBConnection.getConnection();

		this.setSize(600, 720);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setTitle("Управление на автосервиз");
		
		tab.add(userPanel,"Клиенти");
		userPanel.setLayout(new GridLayout(3,1));

		userPanel.add(userPanel1);

		userPanel1.setLayout(new GridLayout(3,2));
		userPanel1.add(userNameL);userPanel1.add(userNameTF);
		userPanel1.add(userPhoneL);userPanel1.add(userPhoneTF);
		userPanel1.add(userEmailL);userPanel1.add(userEmailTF);

		userPanel.add(userPanel2);
		userPanel2.add(userAdd);userPanel2.add(userDel);userPanel2.add(userEdit);userPanel2.add(userSearch);userPanel2.add(userGiveAll);
		// ������ 
		userAdd.addActionListener(new AddUserDB());
		userDel.addActionListener(new DelUserDB());
		userEdit.addActionListener(new EditUserDB());
		userSearch.addActionListener(new SearchUserDB());
		userGiveAll.addActionListener(e -> refreshUserTable());
		
		userPanel.add(userPanel3);
		userScroll.setPreferredSize(new Dimension(550, 200));
		userPanel3.add(userScroll);
		
		tab.add(carPanel,"Коли");
		carPanel.setLayout(new GridLayout(3,1));

		carPanel.add(carPanel1);
			
		carPanel1.setLayout(new GridLayout(5,2));
		carPanel1.add(carVehicleNumberL);carPanel1.add(carVehicleNumberTF);
		carPanel1.add(carBrandL);carPanel1.add(carBrandTF);
		carPanel1.add(carModelL);carPanel1.add(carModelTF);
		carPanel1.add(carProductionYearL);carPanel1.add(carProductionYearTF);
		carPanel1.add(carOwnerL);carPanel1.add(carOwnerCB);
		
		carPanel.add(carPanel2);
		carPanel2.add(carAdd);carPanel2.add(carDel);carPanel2.add(carEdit);carPanel2.add(carSearch);carPanel2.add(carGiveAll);

		carAdd.addActionListener(new AddCarDB());
		carDel.addActionListener(new DelCarDB());
		carEdit.addActionListener(new EditCarDB());
		carSearch.addActionListener(new SearchCarDB());
		carGiveAll.addActionListener(e -> refreshCarTable());
		
		carPanel.add(carPanel3);
		carScroll.setPreferredSize(new Dimension(550, 200));
		carPanel3.add(carScroll);
		
		tab.add(repairPanel,"Ремонт");
		repairPanel.setLayout(new GridLayout(3,1));

		repairPanel.add(repairPanel1);
			
		repairPanel1.setLayout(new GridLayout(4,2));
		repairPanel1.add(repairDesrciptionL);repairPanel1.add(repairDesrciptionTF);
		repairPanel1.add(repairCostL);repairPanel1.add(repairCostTF);
		repairPanel1.add(repairDateL);repairPanel1.add(repairDateTF);
		repairPanel1.add(repairCarL);repairPanel1.add(repairCarCB);
		
		repairPanel.add(repairPanel2);
		repairPanel2.add(repairAdd);repairPanel2.add(repairDel);repairPanel2.add(repairEdit);repairPanel2.add(repairSearch);repairPanel2.add(repairGiveAll);
		// ������ 
		repairAdd.addActionListener(new AddRepairDB());
		repairDel.addActionListener(new DelRepairDB());
		repairEdit.addActionListener(new EditRepairDB());
		repairSearch.addActionListener(new SearchRepairDB());
		repairGiveAll.addActionListener(e -> refreshRepairTable());
		
		repairPanel.add(repairPanel3);
		repairScroll.setPreferredSize(new Dimension(550, 200));
		repairPanel3.add(repairScroll);
		
		tab.add(mechanicPanel,"Механници");
		mechanicPanel.setLayout(new GridLayout(3,1));

		mechanicPanel.add(mechanicPanel1);
	
		mechanicPanel1.setLayout(new GridLayout(3,2));
		mechanicPanel1.add(mechanicNameL);mechanicPanel1.add(mechanicNameTF);
		mechanicPanel1.add(mechanicSpecializationL);mechanicPanel1.add(mechanicSpecializationTF);
		mechanicPanel1.add(mechanicRepairL);mechanicPanel1.add(mechanicRepairCB);
		
		mechanicPanel.add(mechanicPanel2);
		mechanicPanel2.add(mechanicAdd);mechanicPanel2.add(mechanicDel);mechanicPanel2.add(mechanicEdit);
		mechanicPanel2.add(mechanicSearch);mechanicPanel2.add(mechanicGiveAll);
		// ������ 
		mechanicAdd.addActionListener(new AddMechanicDB());
		mechanicDel.addActionListener(new DelMechanicDB());
		mechanicEdit.addActionListener(new EditMechanicDB());
		mechanicSearch.addActionListener(new SearchMechanicDB());
		mechanicGiveAll.addActionListener(e -> refreshMechanicTable());
		
		mechanicPanel.add(mechanicPanel3);
		mechanicScroll.setPreferredSize(new Dimension(550, 200));
		mechanicPanel3.add(mechanicScroll);
		
		tab.add(referencePanel,"Справка");
		referencePanel.setLayout(new GridLayout(3,1));

		referencePanel.add(referencePanel1);
		
		referencePanel1.setLayout(new GridLayout(3,2));
		referencePanel1.add(referenceMechanicNameL);referencePanel1.add(referenceMechanicNameTF);
		referencePanel1.add(referenceStartDateRepairL);referencePanel1.add(referenceStartDateRepairTF);
		referencePanel1.add(referenceEndDateRepairL);referencePanel1.add(referenceEndDateRepairTF);
		
		referencePanel.add(referencePanel2);
		referencePanel2.add(referenceInfoBtn);
		// ������ 
		referenceInfoBtn.addActionListener(new GiveReferenceDB());
		
		referencePanel.add(referencePanel3);
		referenceScroll.setPreferredSize(new Dimension(550, 200));
		referencePanel3.add(referenceScroll);
		
		
		this.add(tab);
		this.loadClientsIntoComboBox();
		this.loadCarsIntoComboBox();
		this.loadRepairsIntoComboBox();
		
		this.setVisible(true);
		
		this.refreshUserTable();
		this.refreshCarTable();
		this.refreshRepairTable();
		this.refreshMechanicTable();
		userTable.addMouseListener(new MouseActionUserTable());
		carTable.addMouseListener(new MouseActionCarTable());
		repairTable.addMouseListener(new MouseActionRepairTable());
		mechanicTable.addMouseListener(new MouseActionMechanicTable());
		
	}
	
	public void refreshUserTable() {
		//conn=MyDBConnection.getConnection();
		String str="";
		str="select id, name, phone, email from clients";
		try {
			state=conn.prepareStatement(str);
			result=state.executeQuery();
			userTable.setModel(new MyTableModel(result));
			
			userTable.getColumnModel().getColumn(0).setMinWidth(0);
			userTable.getColumnModel().getColumn(0).setMaxWidth(0);
			userTable.getColumnModel().getColumn(0).setWidth(0);

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	class AddUserDB implements ActionListener {
		public void actionPerformed (ActionEvent arg0) {
//			conn=DBConnection.getConnection();
			if (!userNameTF.getText().trim().isEmpty() &&
				    !userPhoneTF.getText().trim().isEmpty() &&
				    !userEmailTF.getText().trim().isEmpty()) {
				String sql="insert into clients (name, phone, email) values(?,?,?)";
				try {
					state=conn.prepareStatement(sql);
					state.setString(1, userNameTF.getText());
					state.setString(2, userPhoneTF.getText());
					state.setString(3, userEmailTF.getText());
					state.execute();
					refreshUserTable();
					loadClientsIntoComboBox();
					
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			
				userNameTF.setText("");
				userPhoneTF.setText("");
				userEmailTF.setText("");
				idClient=-1;
			}else {
			    JOptionPane.showMessageDialog(null, "Моля, попълнете всички полета!");
			}
		}
	}
	
	// �������� ������ "���������" �� ������� �����
	class DelUserDB implements ActionListener {
		public void actionPerformed (ActionEvent arg0) {
//			conn=DBConnection.getConnection();
			if (idClient>0) {
				 int choice = JOptionPane.showConfirmDialog(
			                null,
			                "Може да изтрие други данни, свързани с клиента!\nСигурни ли сте, че искате да продължите?",
			                "Потвърждение за изтриване",
			                JOptionPane.OK_CANCEL_OPTION,
			                JOptionPane.WARNING_MESSAGE
			            );

	            if (choice == JOptionPane.OK_OPTION) {
					String sql="delete from clients where id=?";
					try {
						state=conn.prepareStatement(sql);
						state.setInt(1, idClient);
						state.execute();
						refreshUserTable();
						loadClientsIntoComboBox();
						refreshCarTable();
						refreshRepairTable();
						refreshMechanicTable();
					
					} catch (SQLException e1) {
					// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				
					userNameTF.setText("");
					userPhoneTF.setText("");
					userEmailTF.setText("");
					idClient=-1;
			}
			}
		}
	}
	// �������� ������ "�������" �� ������� �����
	class EditUserDB implements ActionListener {
		public void actionPerformed (ActionEvent arg0) {
//			conn=DBConnection.getConnection();
			if(idClient>0) {
				String sql="update clients set name=?, phone=?, email=? where id=?";
			
				try {
					state=conn.prepareStatement(sql);
					
					state.setString(1, userNameTF.getText());
					state.setString(2, userPhoneTF.getText());
					state.setString(3, userEmailTF.getText());
					state.setInt(4, idClient);
					
					state.execute();
					
					refreshUserTable();
					loadClientsIntoComboBox();
					refreshCarTable();
				
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}

				userNameTF.setText("");
				userPhoneTF.setText("");
				userEmailTF.setText("");
				idClient=-1;
			}
			
		}
	}
	
	class SearchUserDB implements ActionListener {
		public void actionPerformed (ActionEvent arg0) {
//			conn=DBConnection.getConnection();
			if(!userNameTF.getText().isEmpty()) {
				String sql="select * from clients where name LIKE ?";
				try {
					state=conn.prepareStatement(sql);
					state.setString(1, userNameTF.getText().trim() + "%");
					result=state.executeQuery();
					userTable.setModel(new MyTableModel(result));
					
					userTable.getColumnModel().getColumn(0).setMinWidth(0);
					userTable.getColumnModel().getColumn(0).setMaxWidth(0);
					userTable.getColumnModel().getColumn(0).setWidth(0);
					
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
					
				}catch (Exception e2) {
				    e2.printStackTrace(); 
				}
			}
		}
	}
	
	class MouseActionUserTable implements MouseListener{

		// �������� ���������� ����� ��� �� ��������� � ���������� ������
		@Override
		public void mouseClicked(MouseEvent e) {
			
			int row=userTable.getSelectedRow();
			
			// ��������� �� ���������� id �� ������� ��������� 		
			idClient=Integer.parseInt(userTable.getValueAt(row, 0).toString());
			
			userNameTF.setText(userTable.getValueAt(row, 1).toString());
			userPhoneTF.setText(userTable.getValueAt(row, 2).toString());
			userEmailTF.setText(userTable.getValueAt(row, 3).toString());
		
		}

		@Override
		public void mouseEntered(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mouseExited(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mousePressed(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mouseReleased(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}
		
	}
	
	class AddCarDB implements ActionListener {
		public void actionPerformed (ActionEvent arg0) {
//			conn=DBConnection.getConnection();
			if (!carVehicleNumberTF.getText().trim().isEmpty() &&
				    !carBrandTF.getText().trim().isEmpty() &&
				    !carModelTF.getText().trim().isEmpty() &&
				    !carProductionYearTF.getText().trim().isEmpty() &&
				    carOwnerCB.getSelectedIndex() != 0)  {
				
				String selectedClientName = (String) carOwnerCB.getSelectedItem();
				int clientId = clientMap.get(selectedClientName);
				
				String sql="insert into cars (vehicle_number, brand, model, production_year, client_id ) values(?,?,?,?,?)";
				try {
					state=conn.prepareStatement(sql);
					state.setString(1, carVehicleNumberTF.getText());
					state.setString(2, carBrandTF.getText());
					state.setString(3, carModelTF.getText());
					state.setString(4, carProductionYearTF.getText());
					state.setInt(5, clientId);
					state.execute();
					refreshCarTable();
					loadCarsIntoComboBox();
					
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			
				carVehicleNumberTF.setText("");
				carBrandTF.setText("");
				carModelTF.setText("");
				carProductionYearTF.setText("");
				carOwnerCB.setSelectedIndex(0);
				idCar=-1;
			}else {
			    JOptionPane.showMessageDialog(null, "Моля, попълнете всички полета и изберете собственик.");
			}
		}
	}
	
	// �������� ������ "���������" �� ������� �����
	class DelCarDB implements ActionListener {
		public void actionPerformed (ActionEvent arg0) {
//			conn=DBConnection.getConnection();
			if (idCar>0) {
				 int choice = JOptionPane.showConfirmDialog(
			                null,
			                "Може да изтрие други данни, свързани с колата!\nСигурни ли сте, че искате да продължите?",
			                "Потвърждение за изтриване",
			                JOptionPane.OK_CANCEL_OPTION,
			                JOptionPane.WARNING_MESSAGE
			            );

	            if (choice == JOptionPane.OK_OPTION) {
				String sql="delete from cars where id=?";
				try {
					state=conn.prepareStatement(sql);
					state.setInt(1, idCar);
					state.execute();
					refreshCarTable();
					loadCarsIntoComboBox();
					refreshRepairTable();
					refreshMechanicTable();
				
				} catch (SQLException e1) {
				// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			
				carVehicleNumberTF.setText("");
				carBrandTF.setText("");
				carModelTF.setText("");
				carProductionYearTF.setText("");
				carOwnerCB.setSelectedIndex(0);
				idCar=-1;
			}
			}
		}
	}
	// �������� ������ "�������" �� ������� �����
	class EditCarDB implements ActionListener {
		public void actionPerformed (ActionEvent arg0) {
//			conn=DBConnection.getConnection();
			if(idCar>0) {
				String selectedClientName = (String) carOwnerCB.getSelectedItem();
				int clientId = clientMap.get(selectedClientName);
				String sql="update cars set vehicle_number=?, brand=?, model=?, production_year=?, client_id=? where id=?";
			
				try {
					state=conn.prepareStatement(sql);
					
					state.setString(1, carVehicleNumberTF.getText());
					state.setString(2, carBrandTF.getText());
					state.setString(3, carModelTF.getText());
					state.setString(4, carProductionYearTF.getText());
					state.setInt(5, clientId);
					state.setInt(6, idCar);
					
					state.execute();
					
					refreshCarTable();
					loadCarsIntoComboBox();
					refreshRepairTable();
					refreshMechanicTable();
				
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}

				carVehicleNumberTF.setText("");
				carBrandTF.setText("");
				carModelTF.setText("");
				carProductionYearTF.setText("");
				carOwnerCB.setSelectedIndex(0);
			}
			
		}
	}
	
	class SearchCarDB implements ActionListener {
	    public void actionPerformed(ActionEvent arg0) {
	        if (!carVehicleNumberTF.getText().isEmpty()) {
	            String sql = "SELECT cars.id, vehicle_number, brand, model, production_year, clients.name AS owner_name " +
	                         "FROM cars " +
	                         "JOIN clients ON cars.client_id = clients.id " +
	                         "WHERE vehicle_number = ?";
	            try {
	                state = conn.prepareStatement(sql);
	                state.setString(1, carVehicleNumberTF.getText());
	                result = state.executeQuery();
	                carTable.setModel(new MyTableModel(result));

	                if (carTable.getColumnModel().getColumnCount() > 0) {
	                    carTable.getColumnModel().getColumn(0).setMinWidth(0);
	                    carTable.getColumnModel().getColumn(0).setMaxWidth(0);
	                    carTable.getColumnModel().getColumn(0).setWidth(0);
	                }

	            } catch (SQLException e) {
	                e.printStackTrace();
	            } catch (Exception e) {
	                e.printStackTrace();
	            }
	        }
	    }
	}
	public void refreshCarTable() {
	    String str = "SELECT cars.id, vehicle_number, brand, model, production_year, clients.name AS owner_name " +
	                 "FROM cars " +
	                 "JOIN clients ON cars.client_id = clients.id";
	    try {
	        state = conn.prepareStatement(str);
	        result = state.executeQuery();
	        carTable.setModel(new MyTableModel(result));

	        carTable.getColumnModel().getColumn(0).setMinWidth(0);
	        carTable.getColumnModel().getColumn(0).setMaxWidth(0);
	        carTable.getColumnModel().getColumn(0).setWidth(0);

	    } catch (SQLException e) {
	        e.printStackTrace();
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	}
	
	class MouseActionCarTable implements MouseListener{

		// �������� ���������� ����� ��� �� ��������� � ���������� ������
		@Override
		public void mouseClicked(MouseEvent e) {
			
			int row=carTable.getSelectedRow();
			
			// ��������� �� ���������� id �� ������� ��������� 		
			idCar=Integer.parseInt(carTable.getValueAt(row, 0).toString());
			
			carVehicleNumberTF.setText(carTable.getValueAt(row, 1).toString());
			carBrandTF.setText(carTable.getValueAt(row, 2).toString());
			carModelTF.setText(carTable.getValueAt(row, 3).toString());
			carProductionYearTF.setText(carTable.getValueAt(row, 4).toString());
			String ownerName = carTable.getValueAt(row, 5).toString();
			carOwnerCB.setSelectedItem(ownerName);
		}

		@Override
		public void mouseEntered(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mouseExited(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mousePressed(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mouseReleased(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}
		
	}

	class AddRepairDB implements ActionListener {
		public void actionPerformed (ActionEvent arg0) {
//			conn=DBConnection.getConnection();
			String date = repairDateTF.getText();
			if (!repairDesrciptionTF.getText().trim().isEmpty() &&
				    !repairCostTF.getText().trim().isEmpty() &&
				    !date.trim().isEmpty() &&
				    repairCarCB.getSelectedIndex() != 0) {
				
				if (!isValidDateFormat(date)) {
			        JOptionPane.showMessageDialog(null, "Моля, въведете датата в правилен формат: ГГГГ-ММ-ДД (напр. 2025-05-29)");
			        return;
			    }
				String selectedCarName = (String) repairCarCB.getSelectedItem();
				int carId = carMap.get(selectedCarName);
				
				String sql="insert into repairs (description, cost, date, car_id ) values(?,?,?,?)";
				try {
					state=conn.prepareStatement(sql);
					state.setString(1, repairDesrciptionTF.getText());
					state.setString(2, repairCostTF.getText());
					state.setString(3, repairDateTF.getText());
					state.setInt(4, carId);
					state.execute();
					
					refreshRepairTable();
					loadRepairsIntoComboBox();
					
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			
				repairDesrciptionTF.setText("");
				repairCostTF.setText("");
				repairDateTF.setText("");
				repairCarCB.setSelectedIndex(0);
				idRepair=-1;
			}else {
	            JOptionPane.showMessageDialog(null, "Моля, попълнете всички полета и изберете кола.");
	        }
		}
	}
	
	// �������� ������ "���������" �� ������� �����
	class DelRepairDB implements ActionListener {
		public void actionPerformed (ActionEvent arg0) {
//			conn=DBConnection.getConnection();
			if (idRepair>0) {
				 int choice = JOptionPane.showConfirmDialog(
			                null,
			                "Може да изтрие други данни, свързани с ремонта!\nСигурни ли сте, че искате да продължите?",
			                "Потвърждение за изтриване",
			                JOptionPane.OK_CANCEL_OPTION,
			                JOptionPane.WARNING_MESSAGE
			            );

	            if (choice == JOptionPane.OK_OPTION) {
					String sql="delete from repairs where id=?";
					try {
						state=conn.prepareStatement(sql);
						state.setInt(1, idRepair);
						state.execute();
						refreshRepairTable();
						loadRepairsIntoComboBox();
						refreshMechanicTable();
					
					} catch (SQLException e1) {
					// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				
					repairDesrciptionTF.setText("");
					repairCostTF.setText("");
					repairDateTF.setText("");
					repairCarCB.setSelectedIndex(0);
					idRepair=-1;
	            }
			}
		}
	}
	// �������� ������ "�������" �� ������� �����
	class EditRepairDB implements ActionListener {
		public void actionPerformed (ActionEvent arg0) {
//			conn=DBConnection.getConnection();
			if(idRepair>0) {
				
				if (!isValidDateFormat(repairDateTF.getText())) {
			        JOptionPane.showMessageDialog(null, "Моля, въведете датата в правилен формат: ГГГГ-ММ-ДД (напр. 2025-05-29)");
			        return;
				}
				String selectedCarName = (String) repairCarCB.getSelectedItem();
				int cartId = carMap.get(selectedCarName);
				String sql="update repairs set description=?, cost=?, date=?, car_id=? where id=?";
			
				try {
					state=conn.prepareStatement(sql);
					
					state.setString(1, repairDesrciptionTF.getText());
					state.setString(2, repairCostTF.getText());
					state.setString(3, repairDateTF.getText());
					state.setInt(4, cartId);
					state.setInt(5, idRepair);
					
					state.execute();
					
					refreshRepairTable();
					loadRepairsIntoComboBox();
					refreshMechanicTable();
				
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}

				repairDesrciptionTF.setText("");
				repairCostTF.setText("");
				repairDateTF.setText("");
				repairCarCB.setSelectedIndex(0);
			}
			
		}
	}
	class SearchRepairDB implements ActionListener {
		public void actionPerformed (ActionEvent arg0) {
//			conn=DBConnection.getConnection();
			String carDesc = repairCarCB.getSelectedItem().toString();
			if (carDesc != null && !carDesc.trim().isEmpty()) {
				int carId = carMap.get(carDesc);
				String str = "SELECT repairs.id, description, cost, date,"
		    			 + " CONCAT(cars.brand, ' ', cars.model, ' (', cars.vehicle_number, ')') AS car " +
		                 "FROM repairs " +
		                 "JOIN cars ON repairs.car_id = cars.id " +
		                 "WHERE cars.id=?";
		    try {
		        state = conn.prepareStatement(str);
		        state.setInt(1, carId);
		        result = state.executeQuery();
		        repairTable.setModel(new MyTableModel(result));

		        repairTable.getColumnModel().getColumn(0).setMinWidth(0);
		        repairTable.getColumnModel().getColumn(0).setMaxWidth(0);
		        repairTable.getColumnModel().getColumn(0).setWidth(0);

		    } catch (SQLException e) {
		        e.printStackTrace();
		    } catch (Exception e) {
		        e.printStackTrace();
		    }
			}
		}
	}
	
	public void refreshRepairTable() {
	    String str = "SELECT repairs.id, description, cost, date,"
	    			 + " CONCAT(cars.brand, ' ', cars.model, ' (', cars.vehicle_number, ')') AS car " +
	                 "FROM repairs " +
	                 "JOIN cars ON repairs.car_id = cars.id";
	    try {
	        state = conn.prepareStatement(str);
	        result = state.executeQuery();
	        repairTable.setModel(new MyTableModel(result));

	        repairTable.getColumnModel().getColumn(0).setMinWidth(0);
	        repairTable.getColumnModel().getColumn(0).setMaxWidth(0);
	        repairTable.getColumnModel().getColumn(0).setWidth(0);

	    } catch (SQLException e) {
	        e.printStackTrace();
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	}
	
	class MouseActionRepairTable implements MouseListener{

		// �������� ���������� ����� ��� �� ��������� � ���������� ������
		@Override
		public void mouseClicked(MouseEvent e) {
			
			int row=repairTable.getSelectedRow();
			
			// ��������� �� ���������� id �� ������� ��������� 		
			idRepair=Integer.parseInt(repairTable.getValueAt(row, 0).toString());
			
			repairDesrciptionTF.setText(repairTable.getValueAt(row, 1).toString());
			repairCostTF.setText(repairTable.getValueAt(row, 2).toString());
			repairDateTF.setText(repairTable.getValueAt(row, 3).toString());
			String carName = repairTable.getValueAt(row, 4).toString();
			repairCarCB.setSelectedItem(carName);
		}

		@Override
		public void mouseEntered(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mouseExited(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mousePressed(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mouseReleased(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}
		
	}
	
	public void refreshMechanicTable() {
		//conn=MyDBConnection.getConnection();
		String str="";
		str = "SELECT m.id, m.name, m.specialization, CONCAT('Кола:', c.vehicle_number, '-', r.description ) AS repair_car " +
		        "FROM mechanics m " +
		        "LEFT JOIN repairmechanics mr ON m.id = mr.mechanic_id " +
		        "LEFT JOIN repairs r ON mr.repair_id = r.id " +
		        "LEFT JOIN cars c ON r.car_id = c.id " +
		        "WHERE c.vehicle_number IS NOT NULL AND r.description IS NOT NULL";
		        
		    try {
		        state = conn.prepareStatement(str);
		        result = state.executeQuery();
		        mechanicTable.setModel(new MyTableModel(result));

		        mechanicTable.getColumnModel().getColumn(0).setMinWidth(0);
		        mechanicTable.getColumnModel().getColumn(0).setMaxWidth(0);
		        mechanicTable.getColumnModel().getColumn(0).setWidth(0);

		    } catch (SQLException e) {
		        e.printStackTrace();
		        
		    }catch (Exception e) {
		        e.printStackTrace();
		    }
		 
	}

	
	class AddMechanicDB implements ActionListener {
	    public void actionPerformed(ActionEvent arg0) {
	    	PreparedStatement insertMechanicStmt = null;
	    	PreparedStatement insertLinkStmt = null;
	    	
	    	 if (!mechanicNameTF.getText().trim().isEmpty() && !mechanicSpecializationTF.getText().trim().isEmpty() && mechanicRepairCB.getSelectedIndex() != 0) {
	            try {
	                String insertMechanic = "INSERT INTO mechanics (name, specialization) VALUES (?, ?)";
	                insertMechanicStmt = conn.prepareStatement(insertMechanic, PreparedStatement.RETURN_GENERATED_KEYS);
	                insertMechanicStmt.setString(1, mechanicNameTF.getText());
	                insertMechanicStmt.setString(2, mechanicSpecializationTF.getText());
	                insertMechanicStmt.executeUpdate();

	                ResultSet keys = insertMechanicStmt.getGeneratedKeys();
	                if (keys.next()) {
	                    idMechanic = keys.getInt(1);
	                }

	                // Вземаме избрания ремонт от ComboBox
	                String selectedRepair = (String) mechanicRepairCB.getSelectedItem();
	                if (selectedRepair != null && repairMap.containsKey(selectedRepair)) {
	                    int repairId = repairMap.get(selectedRepair);

	                    String insertLink = "INSERT INTO repairmechanics (mechanic_id, repair_id) VALUES (?, ?)";
	                    insertLinkStmt = conn.prepareStatement(insertLink);
	                    insertLinkStmt.setInt(1, idMechanic);
	                    insertLinkStmt.setInt(2, repairId);
	                    insertLinkStmt.executeUpdate();
	                }

	                refreshMechanicTable();

	            } catch (SQLException e1) {
	                e1.printStackTrace();
	            } finally {
	                try {
	                    if (insertMechanicStmt != null) insertMechanicStmt.close();
	                    if (insertLinkStmt != null) insertLinkStmt.close();
	                } catch (SQLException e) {
	                    e.printStackTrace();
	                }
	            
	            mechanicNameTF.setText("");
	            mechanicSpecializationTF.setText("");
	            mechanicRepairCB.setSelectedIndex(0);
	        }
	    }else {
            JOptionPane.showMessageDialog(null, "Моля, попълнете всички полета и изберете ремонт.");
        }
	}
	}
	// �������� ������ "���������" �� ������� �����
    class DelMechanicDB implements ActionListener {
        public void actionPerformed(ActionEvent arg0) {
            if (idMechanic > 0) {
                try {
                    String sql1 = "DELETE FROM repairmechanics WHERE mechanic_id = ?";
                    state = conn.prepareStatement(sql1);
                    state.setInt(1, idMechanic);
                    state.executeUpdate();

                    String sql12 = "DELETE FROM mechanics WHERE id = ?";
                    state = conn.prepareStatement(sql12);
                    state.setInt(1, idMechanic);
                    state.executeUpdate();

                    refreshMechanicTable();

                } catch (SQLException e1) {
                    e1.printStackTrace();
                }

                mechanicNameTF.setText("");
                mechanicSpecializationTF.setText("");
                mechanicRepairCB.setSelectedIndex(0);
                idMechanic = -1;
            }
        }
    }

	// �������� ������ "�������" �� ������� �����
	class EditMechanicDB implements ActionListener {
		public void actionPerformed (ActionEvent arg0) {
//			conn=DBConnection.getConnection();
			if (idMechanic > 0) {
			    try {
			        String sql = "UPDATE mechanics SET name=?, specialization=? WHERE id=?";
			        state = conn.prepareStatement(sql);
			        state.setString(1, mechanicNameTF.getText());
			        state.setString(2, mechanicSpecializationTF.getText());
			        state.setInt(3, idMechanic);
			        state.executeUpdate();

			        String selectedRepairDesc = (String) mechanicRepairCB.getSelectedItem();
			        if (selectedRepairDesc != null && repairMap.containsKey(selectedRepairDesc)) {
			            int repairId = repairMap.get(selectedRepairDesc);

			            // Проверяваме дали вече има връзка, ако не — вмъкваме, ако да — обновяваме
			            String checkSql = "SELECT COUNT(*) FROM repairmechanics WHERE mechanic_id = ?";
			            state = conn.prepareStatement(checkSql);
			            state.setInt(1, idMechanic);
			            ResultSet rs = state.executeQuery();
			            rs.next();
			            int count = rs.getInt(1);

			            if (count > 0) {
			                String updateJoin = "UPDATE repairmechanics SET repair_id = ? WHERE mechanic_id = ?";
			                state = conn.prepareStatement(updateJoin);
			                state.setInt(1, repairId);
			                state.setInt(2, idMechanic);
			                state.executeUpdate();
			            } else {
			                String insertJoin = "INSERT INTO repairmechanics (mechanic_id, repair_id) VALUES (?, ?)";
			                state = conn.prepareStatement(insertJoin);
			                state.setInt(1, idMechanic);
			                state.setInt(2, repairId);
			                state.executeUpdate();
			            }
			        }

			        refreshMechanicTable();

			        mechanicNameTF.setText("");
			        mechanicSpecializationTF.setText("");
			        mechanicRepairCB.setSelectedIndex(0);
			        idMechanic = -1;

			    } catch (SQLException e1) {
			        e1.printStackTrace();
			    }
			}
		}
	}
	
	class SearchMechanicDB implements ActionListener {
		public void actionPerformed (ActionEvent arg0) {
//			conn=DBConnection.getConnection();
			String name = mechanicNameTF.getText();
			if(!name.isEmpty()) {
				String str="";
				str = "SELECT m.id, m.name, m.specialization, CONCAT('Кола:', c.vehicle_number, '-', r.description ) AS repair_car " +
				        "FROM mechanics m " +
				        "LEFT JOIN repairmechanics mr ON m.id = mr.mechanic_id " +
				        "LEFT JOIN repairs r ON mr.repair_id = r.id " +
				        "LEFT JOIN cars c ON r.car_id = c.id " +
				        "WHERE m.name LIKE ? AND c.vehicle_number IS NOT NULL AND r.description IS NOT NULL";
				        
				    try {
				        state = conn.prepareStatement(str);
				        state.setString(1, "%" + name + "%");
				        result = state.executeQuery();
				        mechanicTable.setModel(new MyTableModel(result));

				        mechanicTable.getColumnModel().getColumn(0).setMinWidth(0);
				        mechanicTable.getColumnModel().getColumn(0).setMaxWidth(0);
				        mechanicTable.getColumnModel().getColumn(0).setWidth(0);

				    } catch (SQLException e) {
				        e.printStackTrace();
				        
				    }catch (Exception e) {
				        e.printStackTrace();
				    }				 
			}
		}
	}
	
	class MouseActionMechanicTable implements MouseListener{

		// �������� ���������� ����� ��� �� ��������� � ���������� ������
		@Override
		public void mouseClicked(MouseEvent e) {
			
			int row=mechanicTable.getSelectedRow();
			
			// ��������� �� ���������� id �� ������� ��������� 		
			idMechanic=Integer.parseInt(mechanicTable.getValueAt(row, 0).toString());
			
			mechanicNameTF.setText(mechanicTable.getValueAt(row, 1).toString());
			mechanicSpecializationTF.setText(mechanicTable.getValueAt(row, 2).toString());
			String carDesc = mechanicTable.getValueAt(row, 3).toString().trim();

			for (int i = 0; i < mechanicRepairCB.getItemCount(); i++) {
			    Object item = mechanicRepairCB.getItemAt(i);
			    if (item != null && item.toString().trim().equals(carDesc)) {
			        mechanicRepairCB.setSelectedIndex(i);
			        break;
			    } 
			}
		}

		@Override
		public void mouseEntered(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mouseExited(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mousePressed(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mouseReleased(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}
		
	}
	class GiveReferenceDB implements ActionListener {
	    public void actionPerformed(ActionEvent e) {
	        String mechanicName = referenceMechanicNameTF.getText().trim();
	        String startDateStr = referenceStartDateRepairTF.getText().trim(); // очакваме yyyy-MM-dd
	        String endDateStr = referenceEndDateRepairTF.getText().trim();     // очакваме yyyy-MM-dd

	        if (mechanicName.isEmpty() || startDateStr.isEmpty() || endDateStr.isEmpty()) {
	            JOptionPane.showMessageDialog(null, "Моля, попълнете всички полета.");
	            return;
	        }
	        
	        if (!isValidDateFormat(referenceStartDateRepairTF.getText()) || !isValidDateFormat(referenceEndDateRepairTF.getText())) {
		        JOptionPane.showMessageDialog(null, "Моля, въведете датата в правилен формат: ГГГГ-ММ-ДД (напр. 2025-05-29)");
		        return;
			}

	        try {
	            String sql = """
	                SELECT r.id, r.date, r.description, r.cost, c.vehicle_number AS vehicle_number
	                FROM repairs r
	                JOIN repairmechanics rm ON r.id = rm.repair_id
	                JOIN mechanics m ON rm.mechanic_id = m.id
	                JOIN cars c ON r.car_id = c.id
	                WHERE LOWER(m.name) LIKE LOWER(?) AND r.date BETWEEN ? AND ?
	                ORDER BY r.date
	            """;

	            state = conn.prepareStatement(sql);
	            state.setString(1, "%" + mechanicName + "%");
	            state.setDate(2, Date.valueOf(startDateStr));
	            state.setDate(3, Date.valueOf(endDateStr));

	            result = state.executeQuery();
	            referenceTable.setModel(new MyTableModel(result));

	            referenceTable.getColumnModel().getColumn(0).setMinWidth(0);
	            referenceTable.getColumnModel().getColumn(0).setMaxWidth(0);
	            referenceTable.getColumnModel().getColumn(0).setWidth(0);

	        } catch (SQLException ex) {
	            ex.printStackTrace();
	            JOptionPane.showMessageDialog(null, "Грешка при заявката: " + ex.getMessage());
	        } catch (IllegalArgumentException ex) {
	            JOptionPane.showMessageDialog(null, "Невалиден формат на дата. Използвайте yyyy-MM-dd.");
	        }catch (Exception e2) {
		        e2.printStackTrace();
		    }
	    }
	}

	
	private void loadClientsIntoComboBox() {
		carOwnerCB.removeAllItems(); // изчистване на старото съдържание
	    clientMap.clear();
	    
	    carOwnerCB.addItem("");
	    try {
	        String sql = "SELECT id, name FROM Clients";
	        state = conn.prepareStatement(sql);
	        result = state.executeQuery();

	        while (result.next()) {
	            int id = result.getInt("id");
	            String name = result.getString("name");

	            carOwnerCB.addItem(name);          
	            clientMap.put(name, id);  
	        }
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	    
	}
	
	private void loadCarsIntoComboBox() {
	    repairCarCB.removeAllItems();
	    carMap.clear();
	    
	    repairCarCB.addItem("");
	    try {
	        String sql = "SELECT id, vehicle_number, brand, model FROM Cars";
	        state = conn.prepareStatement(sql);
	        result = state.executeQuery();

	        while (result.next()) {
	            int id = result.getInt("id");
	            String brand = result.getString("brand");
	            String model = result.getString("model");
	            String number = result.getString("vehicle_number");

	            String display = brand + " " + model + " (" + number + ")";
	            repairCarCB.addItem(display);
	            carMap.put(display, id);
	        }
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	}
	
	private void loadRepairsIntoComboBox() {
		mechanicRepairCB.removeAllItems();
	    repairMap.clear(); // Map<String, Integer>

	    mechanicRepairCB.addItem("");
	    try {
	        String sql = 
	            "SELECT r.id, c.vehicle_number, r.description " +
	            "FROM repairs r " +
	            "JOIN cars c ON r.car_id = c.id";
	        state = conn.prepareStatement(sql);
	        result = state.executeQuery();

	        while (result.next()) {
	            int id = result.getInt("id");
	            String number = result.getString("vehicle_number");
	            String desc = result.getString("description");

	            String display = "Кола:" + number + "-" + desc;
	            mechanicRepairCB.addItem(display.toString());
	            repairMap.put(display, id);
	        }
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	}
	
	private boolean isValidDateFormat(String dateStr) {
	    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
	    try {
	        LocalDate.parse(dateStr, formatter);
	        return true;
	    } catch (DateTimeParseException e) {
	        return false;
	    }
	}
}
