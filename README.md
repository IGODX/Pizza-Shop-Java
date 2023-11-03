# Pizza-Shop-Java

  CREATE TABLE pizza( id int not null AUTO_INCREMENT PRIMARY KEY, pizzaName varchar(80) NOT NULL, photoUrl varchar(150) NOT NULL, isCustom tinyint );

   INSERT INTO `pizza`(`pizzaName`, `photoUrl`) VALUES ('Margarita', 'https://www.onceuponachef.com/images/2020/06/Margherita-Pizza-scaled.jpg'), ('Four cheeses', 'https://static.onecms.io/wp-content/uploads/sites/19/2010/04/12/oh-four-cheese-pizza-x.jpg'), ('Caprice', 'https://static.onecms.io/wp-content/uploads/sites/19/2014/07/29/caprese-pizza-ck-x.jpg'), ('Hawaiian', 'https://img.kidspot.com.au/pZnR2nZu/kk/2015/03/hawaiian-pizza-recipe-605894-2.jpg');

   INSERT INTO `pizza`(`pizzaName`, `photoUrl`, `isCustom`) VALUES ('Custom pizza', 'https://res.cloudinary.com/perkchops/image/upload/q_40,fl_lossy,w_600,/v1670157953/product/2022114/pulartnr9jttbvk1wz5k.jpg', 1);

   CREATE TABLE ingredient( id int not null PRIMARY KEY AUTO_INCREMENT, ingredientName varchar(80) NOT NULL );


   CREATE TABLE defaultpizzatoorder( id int not null PRIMARY KEY AUTO_INCREMENT, pizzaId int not null, ingredientId int, pizzaOrderId int not null, FOREIGN KEY (ingredientId) REFERENCES ingredient(id), FOREIGN KEY (pizzaId) REFERENCES pizza(id), FOREIGN KEY (pizzaOrderId) REFERENCES orderlocation(id) );

   INSERT INTO ingredient (ingredientName) VALUES ('Olives'), ('Capersie'), ('Extra cheese');

   CREATE TABLE pizzaOrder ( id INT NOT NULL AUTO_INCREMENT PRIMARY KEY, place VARCHAR(60) NOT NULL );

   CREATE TABLE customPizzaType( id int not null AUTO_INCREMENT PRIMARY KEY, typeName varchar(60) NOT NULL );

   INSERT INTO `custompizzatype`(`typeName`) VALUES ('Main ingredient'), ('Secondary ingredient'), ('Sauce');

   CREATE TABLE customPizzaIngredient( id int not null AUTO_INCREMENT PRIMARY KEY, ingredientName varchar(60) not null, typeId int not null, FOREIGN KEY (typeId) REFERENCES customPizzaType(id) );

   INSERT INTO `custompizzaingredient`(`ingredientName`, `typeId`) VALUES ('Chicken','1'), ('Barbecue-Pork','1'), ('Seafood', '1'), ('Mushrooms', '2'), ('Pineapple', '2'), ('Hot pepper', '2'), ('White sauce', '3'), ('Tomato sauce', '3'), ('Cream sauce', '3')

   CREATE TABLE custompizzatoorder( id int not null AUTO_INCREMENT PRIMARY KEY, pizzaId int not null, ingredientId int not null, pizzaOrderId int not null, FOREIGN KEY (pizzaId) REFERENCES pizza(id), FOREIGN KEY (ingredientId) REFERENCES custompizzaingredient(id), FOREIGN KEY (pizzaOrderId) REFERENCES orderlocation(id) );

   CREATE TABLE orderlocation( id int not null AUTO_INCREMENT PRIMARY KEY, locationName varchar(100) not null );

   CREATE TABLE pizzashoplocation( id int not null AUTO_INCREMENT PRIMARY KEY, lon double not null, lat double not null );

   INSERT INTO `pizzashoplocation`(`lon`, `lat`) VALUES ('33.39650630950928','47.9103231357631');

    CREATE TABLE myuser( id int not null PRIMARY KEY AUTO_INCREMENT, firstname varchar(60) not null, telephoneNumber varchar(60) not null, email varchar(60) not null, orderId int not null, FOREIGN KEY (orderId) REFERENCES orderlocation(id) );
    
