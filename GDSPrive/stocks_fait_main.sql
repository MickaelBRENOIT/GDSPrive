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
    date_naissance datetime NOT NULL,
    date_embauche datetime NOT NULL,
    adresse_utilisateur varchar(100) NOT NULL,
    ce_role INT(11) UNSIGNED NOT NULL,
    numero_utilisateur varchar(10) NOT NULL,
    FOREIGN KEY (ce_role) REFERENCES role(id_role)
) ENGINE=InnoDB;

INSERT INTO `utilisateur` (`id_utilisateur`, `nom_utilisateur`, `prenom_utilisateur`, `email_utilisateur`, `password_utilisateur`, `date_naissance`, `date_embauche`, `adresse_utilisateur`, `ce_role`, `numero_utilisateur`) VALUES
(1, 'BRENOIT', 'Mickael', 'mickael.brenoit@uha.fr', '0000', '1994-05-30 00:00:00', '2016-10-10 00:00:00', '6 rue de Sochaux, 68200 Mulhouse', 1, '0389010203'),
(2, 'DUPONT', 'Pierre', 'pierre.dupont@uha.fr', '1234', '1988-02-10 00:00:00', '2016-04-19 00:00:00', '17 rue du Soleil, 67000 Strasbourg', 2, '0389070809');

CREATE TABLE fournisseur (
    id_fournisseur INT(11) UNSIGNED PRIMARY KEY AUTO_INCREMENT,
    societe_fournisseur varchar(50),
    adresse_fournisseur varchar(50) NOT NULL,
    domaine_fournisseur varchar(50) NOT NULL,
    numero_fournisseur varchar(10) NOT NULL
) ENGINE=InnoDB;

INSERT INTO `fournisseur` (`id_fournisseur`, `societe_fournisseur`, `adresse_fournisseur`, `domaine_fournisseur`, `numero_fournisseur`) VALUES
(1, 'Carrefour', '12 rue des champs', 'supermarché', '0102030405'),
(2, 'Cora', '62 rue des portes', 'hypermarché', '0605020301'),
(3, 'Eureka', '42 rue des programmeurs', 'Informatique', '0389010203');

CREATE TABLE client (
    id_client INT(11) UNSIGNED PRIMARY KEY AUTO_INCREMENT,
    societe_client varchar(50),
    adresse_client varchar(50) NOT NULL,
    domaine_client varchar(50) NOT NULL,
    numero_client varchar(10) NOT NULL
) ENGINE=InnoDB;