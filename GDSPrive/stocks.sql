-- phpMyAdmin SQL Dump
-- version 4.1.14
-- http://www.phpmyadmin.net
--
-- Client :  127.0.0.1
-- Généré le :  Ven 17 Février 2017 à 13:40
-- Version du serveur :  5.6.17
-- Version de PHP :  5.5.12

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

--
-- Base de données :  `stocks`
--

-- --------------------------------------------------------

--
-- Structure de la table `client`
--

CREATE TABLE IF NOT EXISTS `client` (
  `id_client` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `societe_client` varchar(50) DEFAULT NULL,
  `adresse_client` varchar(50) NOT NULL,
  `domaine_client` varchar(50) NOT NULL,
  `numero_client` varchar(10) NOT NULL,
  PRIMARY KEY (`id_client`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=5 ;

--
-- Contenu de la table `client`
--

INSERT INTO `client` (`id_client`, `societe_client`, `adresse_client`, `domaine_client`, `numero_client`) VALUES
(1, '', '12 rue des glands, 68200 Mulhouse', 'Particulier', '0308090603'),
(2, 'Clemessy Motors', '53 boulevards des peupliers', 'Production', '0389365421'),
(3, 'Eureka', '47 rue du tournessol, 68100 Mulhouse', 'Informatique', '0389654712'),
(4, 'IBM', '36 rue des flaques, 67000 Strasbourg', 'Informatique', '0387523641');

-- --------------------------------------------------------

--
-- Structure de la table `fournisseur`
--

CREATE TABLE IF NOT EXISTS `fournisseur` (
  `id_fournisseur` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `societe_fournisseur` varchar(50) DEFAULT NULL,
  `adresse_fournisseur` varchar(50) NOT NULL,
  `domaine_fournisseur` varchar(50) NOT NULL,
  `numero_fournisseur` varchar(10) NOT NULL,
  PRIMARY KEY (`id_fournisseur`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=4 ;

--
-- Contenu de la table `fournisseur`
--

INSERT INTO `fournisseur` (`id_fournisseur`, `societe_fournisseur`, `adresse_fournisseur`, `domaine_fournisseur`, `numero_fournisseur`) VALUES
(1, 'Carrefour', '12 rue des champs', 'supermarché', '0102030405'),
(2, 'Cora', '62 rue des portes', 'hypermarché', '0605020301'),
(3, 'Eureka', '42 rue des programmeurs', 'Informatique', '0389010203');

-- --------------------------------------------------------

--
-- Structure de la table `role`
--

CREATE TABLE IF NOT EXISTS `role` (
  `id_role` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `nom_role` varchar(20) NOT NULL,
  PRIMARY KEY (`id_role`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=3 ;

--
-- Contenu de la table `role`
--

INSERT INTO `role` (`id_role`, `nom_role`) VALUES
(1, 'administrateur'),
(2, 'utilisateur');

-- --------------------------------------------------------

--
-- Structure de la table `utilisateur`
--

CREATE TABLE IF NOT EXISTS `utilisateur` (
  `id_utilisateur` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `nom_utilisateur` varchar(50) NOT NULL,
  `prenom_utilisateur` varchar(50) NOT NULL,
  `email_utilisateur` varchar(50) NOT NULL,
  `password_utilisateur` varchar(50) NOT NULL,
  `date_naissance` datetime NOT NULL,
  `date_embauche` datetime NOT NULL,
  `adresse_utilisateur` varchar(100) NOT NULL,
  `ce_role` int(11) unsigned NOT NULL,
  `numero_utilisateur` varchar(10) NOT NULL,
  PRIMARY KEY (`id_utilisateur`),
  KEY `ce_role` (`ce_role`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=3 ;

--
-- Contenu de la table `utilisateur`
--

INSERT INTO `utilisateur` (`id_utilisateur`, `nom_utilisateur`, `prenom_utilisateur`, `email_utilisateur`, `password_utilisateur`, `date_naissance`, `date_embauche`, `adresse_utilisateur`, `ce_role`, `numero_utilisateur`) VALUES
(1, 'BRENOIT', 'Mickael', 'mickael.brenoit@uha.fr', '0000', '1994-05-30 00:00:00', '2016-10-10 00:00:00', '6 rue de Sochaux, 68200 Mulhouse', 1, '0389010203'),
(2, 'DUPONT', 'Pierre', 'pierre.dupont@uha.fr', '1234', '1988-02-10 00:00:00', '2016-04-19 00:00:00', '17 rue du Soleil, 67000 Strasbourg', 2, '0389070809');

--
-- Contraintes pour les tables exportées
--

--
-- Contraintes pour la table `utilisateur`
--
ALTER TABLE `utilisateur`
  ADD CONSTRAINT `utilisateur_ibfk_1` FOREIGN KEY (`ce_role`) REFERENCES `role` (`id_role`);

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
