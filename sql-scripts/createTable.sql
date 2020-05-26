CREATE TABLE tbl_orders (
		id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
		distance INT NOT NULL,
		status VARCHAR(100) NOT NULL);

INSERT INTO `tbl_orders` (`id`, `distance`, `status`) VALUES (1,4464,'UNASSIGNED');
INSERT INTO `tbl_orders` (`id`, `distance`, `status`) VALUES (2,3029,'UNASSIGNED');
INSERT INTO `tbl_orders` (`id`, `distance`, `status`) VALUES (3,6963,'UNASSIGNED');
INSERT INTO `tbl_orders` (`id`, `distance`, `status`) VALUES (4,2353,'UNASSIGNED');
