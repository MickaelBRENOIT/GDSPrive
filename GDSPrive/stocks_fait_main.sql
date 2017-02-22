DROP TABLE IF EXISTS commande_article;
DROP TABLE IF EXISTS produit;
DROP TABLE IF EXISTS commande;
DROP TABLE IF EXISTS client;
DROP TABLE IF EXISTS fournisseur;
DROP TABLE IF EXISTS utilisateur;
DROP TABLE IF EXISTS role;

CREATE TABLE role (
    id_role INT(11) UNSIGNED PRIMARY KEY AUTO_INCREMENT,
    nom_role varchar(20) NOT NULL
) ENGINE=InnoDB; 

INSERT INTO `role` (`id_role`, `nom_role`) VALUES
(1, 'administrateur'),
(2, 'utilisateur');

CREATE TABLE utilisateur (
    id_utilisateur INT(11) UNSIGNED PRIMARY KEY AUTO_INCREMENT,
    nom_utilisateur varchar(50) NOT NULL,
    prenom_utilisateur varchar(50) NOT NULL,
    email_utilisateur varchar(50) NOT NULL,
    password_utilisateur varchar(50) NOT NULL,
    date_naissance date NOT NULL,
    date_embauche date NOT NULL,
    adresse_utilisateur varchar(100) NOT NULL,
    ce_role INT(11) UNSIGNED NOT NULL,
    numero_utilisateur varchar(10) NOT NULL,
    FOREIGN KEY (ce_role) REFERENCES role(id_role)
) ENGINE=InnoDB;

INSERT INTO `utilisateur` (`id_utilisateur`, `nom_utilisateur`, `prenom_utilisateur`, `email_utilisateur`, `password_utilisateur`, `date_naissance`, `date_embauche`, `adresse_utilisateur`, `ce_role`, `numero_utilisateur`) VALUES
(1, 'BRENOIT', 'Mickael', 'mickael.brenoit@uha.fr', '0000', '1994-05-30', '2016-10-10', '6 rue de Sochaux, 68200 Mulhouse', 1, '0389010203'),
(2, 'DUPONT', 'Pierre', 'pierre.dupont@uha.fr', '1234', '1988-02-10', '2016-04-19', '17 rue du Soleil, 67000 Strasbourg', 2, '0389070809');

CREATE TABLE fournisseur (
    id_fournisseur INT(11) UNSIGNED PRIMARY KEY AUTO_INCREMENT,
    societe_fournisseur varchar(50) NOT NULL,
    adresse_fournisseur varchar(50) NOT NULL,
    domaine_fournisseur varchar(50) NOT NULL,
    numero_fournisseur varchar(10) NOT NULL,
    email_fournisseur varchar(50) NOT NULL
) ENGINE=InnoDB;

INSERT INTO `fournisseur` (`id_fournisseur`, `societe_fournisseur`, `adresse_fournisseur`, `domaine_fournisseur`, `numero_fournisseur`, `email_fournisseur`) VALUES
(1, 'Carrefour', '12 rue des champs', 'supermarché', '0102030405', 'carrefour@gmail.com'),
(2, 'Cora', '62 rue des portes', 'hypermarché', '0605020301', 'cora@gmail.com'),
(3, 'Eureka', '42 rue des programmeurs', 'Informatique', '0389010203', 'eureka@gmail.com');

CREATE TABLE client (
    id_client INT(11) UNSIGNED PRIMARY KEY AUTO_INCREMENT,
    societe_client varchar(50) NOT NULL,
    adresse_client varchar(50) NOT NULL,
    domaine_client varchar(50) NOT NULL,
    numero_client varchar(10) NOT NULL,
    email_client varchar(50) NOT NULL
) ENGINE=InnoDB;

INSERT INTO `client` (`id_client`, `societe_client`, `adresse_client`, `domaine_client`, `numero_client`, `email_client`) VALUES
(1, 'Chaiseland', '12 rue des glands, 68200 Mulhouse', 'Ebenisterie', '0308090603', 'exemple01@gmail.com'),
(2, 'Clemessy Motors', '53 boulevards des peupliers', 'Production', '0389365421', 'exemple02@yahoo.fr'),
(3, 'Eureka', '47 rue du tournessol, 68100 Mulhouse', 'Informatique', '0389654712', 'exemple03@uha.fr'),
(4, 'IBM', '36 rue des flaques, 67000 Strasbourg', 'Informatique', '0387523641', 'exemple04@laposte.net');

CREATE TABLE commande (
    id_commande INT(11) UNSIGNED PRIMARY KEY AUTO_INCREMENT,
    ce_client INT(11) UNSIGNED NOT NULL,
    date_commande date NOT NULL,
    date_limite_livraison date NOT NULL,
    date_livraison date NOT NULL,
    FOREIGN KEY (ce_client) REFERENCES client(id_client)
) ENGINE=InnoDB;

INSERT INTO `commande` (`id_commande`, `ce_client`, `date_commande`, `date_limite_livraison`, `date_livraison`) VALUES
(1, 1, '2017-02-01', '2017-02-02', '2017-02-03'),
(2, 2, '2017-02-05', '2017-02-06', '2017-02-07'),
(4, 1, '2017-01-01', '2017-01-02', '2017-01-03');

CREATE TABLE produit (
  id_produit int(11) UNSIGNED PRIMARY KEY AUTO_INCREMENT,
  nom_produit varchar(50) NOT NULL,
  prix_unitaire decimal(11,2) NOT NULL,
  quantite int(11) NOT NULL,
  date_expiration date NOT NULL,
  stock_minimal int(11) UNSIGNED,
  societe_fournisseur varchar(50) NOT NULL,
  ce_fournisseur int(11) UNSIGNED NOT NULL,
  FOREIGN KEY (ce_fournisseur) REFERENCES fournisseur(id_fournisseur)
) ENGINE=InnoDB;

CREATE TABLE commande_article (
  id_commande_article INT(11) UNSIGNED PRIMARY KEY AUTO_INCREMENT,
  ce_commande INT(11) UNSIGNED NOT NULL,
  ce_produit INT(11) UNSIGNED NOT NULL,
  quantite int(11) UNSIGNED NOT NULL,
  FOREIGN KEY (ce_commande) REFERENCES commande(id_commande),
  FOREIGN KEY (ce_produit) REFERENCES produit(id_produit)
) ENGINE=InnoDB;