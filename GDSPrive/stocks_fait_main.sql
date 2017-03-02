DROP TABLE IF EXISTS commande_fournisseur;
DROP TABLE IF EXISTS commande_temporaire;
DROP TABLE IF EXISTS commande_article;
DROP TABLE IF EXISTS commande;
DROP TABLE IF EXISTS produit;
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
    numero_utilisateur varchar(50) NOT NULL,
    FOREIGN KEY (ce_role) REFERENCES role(id_role)
) ENGINE=InnoDB;

INSERT INTO `utilisateur` (`id_utilisateur`, `nom_utilisateur`, `prenom_utilisateur`, `email_utilisateur`, `password_utilisateur`, `date_naissance`, `date_embauche`, `adresse_utilisateur`, `ce_role`, `numero_utilisateur`) VALUES
(1, 'DUPONT', 'Jean', 'admin@uha.fr', 'admin', '1994-05-30', '2016-10-10', '6 rue des champs, 68200 Mulhouse', 1, '0389010203'),
(2, 'MARTIN', 'Arnaud', 'utilisateur@uha.fr', 'utilisateur', '1988-02-10', '2016-04-19', '17 rue du Soleil, 67000 Strasbourg', 2, '0389070809');

CREATE TABLE fournisseur (
    id_fournisseur INT(11) UNSIGNED PRIMARY KEY AUTO_INCREMENT,
    societe_fournisseur varchar(50) NOT NULL,
    adresse_fournisseur varchar(50) NOT NULL,
    domaine_fournisseur varchar(50) NOT NULL,
    numero_fournisseur varchar(50) NOT NULL,
    email_fournisseur varchar(50) NOT NULL
) ENGINE=InnoDB;

INSERT INTO `fournisseur` (`id_fournisseur`, `societe_fournisseur`, `adresse_fournisseur`, `domaine_fournisseur`, `numero_fournisseur`, `email_fournisseur`) VALUES
(1, 'Cora', '258 rue de Belfort, 68067', 'Supermarché', '0389323838', 'cora@uha.fr'),
(2, 'Leclerc', '7 rue Gay Lussac, 68200', 'Supermarché', '0389354900', 'leclerc@uha.fr'),
(3, 'Eureka', '52 rue de Dornach, 68120', 'Informatique', '0389512020', 'eureka@uha.fr'),
(4, 'Ikea', 'ZAC Parc des collines, 68790 Morschwiller-le-Bas', 'Mobilier', '0969362006', 'ikea@uha.fr'),
(5, 'Atlantis', '43 rue Buffon, 68200 Mulhouse', 'Informatique', '0389334334', 'atlantis@uha.fr');

CREATE TABLE client (
    id_client INT(11) UNSIGNED PRIMARY KEY AUTO_INCREMENT,
    societe_client varchar(50) NOT NULL,
    adresse_client varchar(50) NOT NULL,
    domaine_client varchar(50) NOT NULL,
    numero_client varchar(50) NOT NULL,
    email_client varchar(50) NOT NULL
) ENGINE=InnoDB;

INSERT INTO `client` (`id_client`, `societe_client`, `adresse_client`, `domaine_client`, `numero_client`, `email_client`) VALUES
(1, 'UHA', '2 rue des Frères Lumières, 68100 Mulhouse', 'Université', '0389336000', 'uha@uha.fr'),
(2, 'Chaiseland', '129 rue de Belfort, 68100 Mulhouse', 'Ebénisterie', '0389604637', 'chaiseland@uha.fr'),
(3, 'FNAC', '54 rue du Sauvage, 68100 Mulhouse', 'Magasin', '0825020020', 'fnac@uha.fr');

CREATE TABLE produit (
  id_produit int(11) UNSIGNED PRIMARY KEY AUTO_INCREMENT,
  nom_produit varchar(50) NOT NULL,
  prix_unitaire decimal(11,2) NOT NULL,
  quantite int(11) NOT NULL,
  date_expiration date NOT NULL,
  societe_fournisseur varchar(50) NOT NULL,
  ce_fournisseur int(11) UNSIGNED NOT NULL,
  stock_minimal int(11) UNSIGNED NOT NULL,
  FOREIGN KEY (ce_fournisseur) REFERENCES fournisseur(id_fournisseur)
) ENGINE=InnoDB;

INSERT INTO `produit` (`id_produit`, `nom_produit`, `prix_unitaire`, `quantite`, `date_expiration`, `societe_fournisseur`, `ce_fournisseur`, `stock_minimal`) VALUES
(1, 'Aspirateur', '61.30', 50, '2017-03-31', 'Cora', 1, 10),
(2, 'ERP', '1000000.00', 10, '2017-03-28', 'Eureka', 3, 3),
(3, 'Aspirateur', '59.50', 90, '2017-03-16', 'Leclerc', 2, 15),
(4, 'Ordinateur', '1550.00', 30, '2017-03-22', 'Atlantis', 5, 5),
(5, 'Ecran', '225.00', 300, '2017-03-08', 'Atlantis', 5, 20),
(6, 'Lit', '450.25', 500, '2017-03-16', 'Ikea', 4, 80);

CREATE TABLE commande (
    id_commande INT(11) UNSIGNED PRIMARY KEY AUTO_INCREMENT,
    ce_client INT(11) UNSIGNED NOT NULL,
    date_commande date NOT NULL,
    date_limite_livraison date NOT NULL,
    date_livraison date NOT NULL,
    prix_total decimal(11,2) NOT NULL,
    FOREIGN KEY (ce_client) REFERENCES client(id_client)
) ENGINE=InnoDB;

INSERT INTO `commande` (`id_commande`, `ce_client`, `date_commande`, `date_limite_livraison`, `date_livraison`, `prix_total`) VALUES
(1, 1, '2017-02-02', '2017-03-05', '2017-03-01', '31000.00');

CREATE TABLE commande_article (
  id_commande_article INT(11) UNSIGNED PRIMARY KEY AUTO_INCREMENT,
  ce_commande INT(11) UNSIGNED NOT NULL,
  ce_produit INT(11) UNSIGNED NOT NULL,
  quantite int(11) UNSIGNED NOT NULL,
  FOREIGN KEY (ce_commande) REFERENCES commande(id_commande),
  FOREIGN KEY (ce_produit) REFERENCES produit(id_produit)
) ENGINE=InnoDB;

INSERT INTO `commande_article` (`id_commande_article`, `ce_commande`, `ce_produit`, `quantite`) VALUES
(1, 1, 4, 20);

CREATE TABLE commande_temporaire (
  id_commande_temporaire INT(11) UNSIGNED PRIMARY KEY AUTO_INCREMENT,
  nom_societe varchar(50) NOT NULL,
  nom_produit varchar(50) NOT NULL,
  prix_unitaire decimal(11,2) NOT NULL,
  quantite INT(11) UNSIGNED NOT NULL,
  total decimal(11,2) NOT NULL
) ENGINE=InnoDB;

CREATE TABLE commande_fournisseur (
  id_commande_fournisseur INT(11) UNSIGNED PRIMARY KEY AUTO_INCREMENT,
  ce_produit INT(11) UNSIGNED NOT NULL,
  date_commande date NOT NULL,
	quantite INT(11) UNSIGNED NOT NULL,
  FOREIGN KEY (ce_produit) REFERENCES produit(id_produit)
) ENGINE=InnoDB;