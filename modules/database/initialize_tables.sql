--
-- Dumping data for table `module`
--
INSERT INTO `module` VALUES
  (1,'admin','admin module'),
  (2,'rewards-reporting','This module is for reporting');


--
-- Dumping data for table `group`
--

INSERT INTO `group` VALUES
  (1,'GROUP_SYS_ADMIN','group with the entire authority for the system',1),
  (2,'GROUP_ADM_MANAGER','MAnager group for Admin module administration work',1),
  (3,'GROUP_CC_MANAGER',' Manager group for Customercare module',1),
  (4,'GROUP_CUSTOMERCARE',' Customer Care group including Chefs, Waiters, Cashiers',1),
  (5,'GROUP_RPT_MANAGER',' Manager group for reporting. Responsible for carrying out reporting',2);


--
-- Dumping data for table `permission`
--

INSERT INTO `permission` VALUES
  (1,'ADD_USER','Permission to add user'),
  (2,'EDIT_USER','Permission to modify user'),
  (3,'BLOCK_USER','Permission to remove user from user list and block'),
  (4,'BLOCK_USER_RESTORE','Permission to restore a blocked users'),
  (5,'VIEW_USER','Permission to view the details of user'),
  (6,'ADD_ITEM','Permission to add an item'),
  (7,'EDIT_ITEM','Permission to modify an item'),
  (8,'DELETE_ITEM','Permission to remove an item'),
  (9,'VIEW_ITEMS','Permission to view the details of items'),
  (10,'ADD_PACKAGE','Permission to add an package'),
  (11,'EDIT_PACKAGE','Permission to modify an package'),
  (12,'DELETE_PACKAGE','Permission to remove an package'),
  (13,'VIEW_PACKAGE','Permission to view the details of packages'),
  (14,'ADD_CATEGORY','Permission to add a new category'),
  (15,'EDIT_CATEGORY','Permission to modify a category details'),
  (16,'DELETE_CATEGORY','Permission to remove a category'),
  (17,'VIEW_CATEGORY','Permission to view the details of categories'),
  (18,'APPROVE_COMMENTS','Permission to approve user comments'),
  (19,'DELETE_COMMENTS','Permission to remove a comment'),


  (20,'ADD_GROUP','Permission to add a new group'),
  (21,'EDIT_GROUP','Permission to modify a group'),
  (22,'DELETE_GROUP','Permission to remove a group'),
  (23,'VIEW_GROUP','Permission to view groups'),

  (24,'ADD_PERMISSION','Permission to add permission'),
  (25,'EDIT_PERMISSION','Permission to modify permission'),
  (26,'DELETE_PERMISSION','Permission to delete permission'),

  (27,'ADD_MODULE','Permission to add module'),
  (28,'MODIFY_MODULE','Permission to modify module'),

  (29,'VIEW_REPORTS','Permission to view reports'),#customercare manager and customercare  can view
  (30,'CREATE_REPORTS','Permission to generate a reports'),
  (31,'DELETE_REPORTS','Permission to delete the created reports'),
# common permission
  (32,'VIEW_PROFILE','View the profile'),
  (33,'VIEW_COMMENTS','Permission to view the user comments'),#cutomercare manager and customercare
  (34,'LOGIN','Login to the system, Only for active status');


--
-- Dumping data for table `group_permission`
--

INSERT INTO `group_permission` (`group_id`, `permission_id`) VALUES
  (1,1),(1,2),(1,3),(1,4),(1,5),(1,6),(1,7),(1,8),(1,9),(1,10),(1,11),(1,12),(1,13),(1,14),(1,15),(1,16),(1,17),(1,18),(1,19),(1,20),(1,21),(1,22),(1,23),(1,24),(1,25),(1,26),(1,27),(1,28),(1,29),(1,30),(1,31),(1,32),(1,33),(1,34),
  (2,1),(2,2),(2,3),(2,4),(2,5),(2,20),(2,21),(2,22),(2,23),(2,24),(2,25),(2,26),(2,27),(2,28),(2,32),(2,34),
  (3,1),(3,2),(3,3),(3,4),(3,5),(3,6),(3,7),(3,8),(3,9),(3,10),(3,11),(3,12),(3,13),(3,14),(3,15),(3,16),(3,17),(3,18),(3,19),(3,29),(3,32),(3,33),(3,34),
  (4,29),(4,32),(4,33),(4,34),
  (5,29),(5,30),(5,31),(5,32),(5,34);

--
-- Dumping data for table `staff`
--


INSERT INTO `staff` (`id`, `title`, `username`, `password`, `first_name`, `last_name`, `email`, `mobile`, `address_line1`, `address_line2`, `address_line3`, `designation`, `department`, `branch`, `register_date`, `status`) VALUES
  (1, 'Mr', 'sysadmin', 'a159b7ae81ba3552af61e9731b20870515944538', 'Piumantha', 'Heshan', 'sysadmin@pizzashefu.com', '+94778908976', 'No:2, Dangarama Road', 'Kollupitiya', 'Colombo', 'System Adinistrator', 'Adminintration', 'Colombo', '2015-08-04', 'active'),
  (2, 'Mrs', 'adminmanager', '47597d7f4c61063f651df834bed02203b816d996', 'Piumika', 'Dankotuva', 'adminmanager@pizzashefu.com', '+94710098765', 'No:32, ', 'Kadawatha', 'Gampaha', 'System Manager', 'Adminintration', 'Colombo', '2015-09-22', 'active'),
  (3, 'Mr', 'ccmanager', 'e928bc3e58bde5afa2de18334666022411a5b290', 'Isuru', 'Godakumbura', 'ccmanager@pizzashefu.com', '+94774352231', 'No:556,Raddoluwa', 'Katunayaka', 'Gampaha', 'Customer Manager', 'Adminintration', 'Colombo', '2016-01-12', 'active'),
  (4, 'Mr', 'customercare', 'd9ba6e298a34f4648ce07ec93ef099c58fff8e50', 'Thanuja', 'Abeyrathna', 'customercare@pizzashefu.com', '+94713456722', 'No:54,', 'Pilimathalawa', 'Kandy', 'Cashier', 'Finance', 'Colombo', '2014-10-14', 'active'),
  (5, 'Mrs', 'customercare2', '507db975e3a0024893db4aba771625dc2131d135', 'Manjula', 'Priyanthi', 'customercare2@pizzashefu.com', '+94712340098', 'No:43', 'Baseline Road', 'Colombo', 'Main Chef', 'Logistic', 'Colombo', '2016-04-26', 'active'),
  (6, 'Mr', 'reportmanager', 'e0ef0b3a7d4e03ed6a746896826ad04dffa0c199', 'Nadun', 'Udayanga', 'reportmanager@pizzashefu.com', '+94772345670', 'No:889,', 'Maligawaththa Lane', 'Kandy', 'Reporting Manager', 'Reporting', 'Colombo', '2014-03-17', 'active');

--
-- Dumping data for table `group_staff`
--

INSERT INTO `group_staff` (`group_id`, `staff_id`) VALUES
  (1, 1),
  (2, 2),
  (3, 3),
  (4, 4),
  (4, 5),
  (5, 6);