-- phpMyAdmin SQL Dump
-- version 4.6.4
-- https://www.phpmyadmin.net/
--
-- Client :  127.0.0.1
-- Généré le :  Mer 01 Mars 2017 à 22:48
-- Version du serveur :  5.7.14
-- Version de PHP :  5.6.25

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de données :  `stocks`
--

-- --------------------------------------------------------

--
-- Structure de la table `client`
--

CREATE TABLE `client` (
  `id_client` int(11) UNSIGNED NOT NULL,
  `societe_client` varchar(50) NOT NULL,
  `adresse_client` varchar(50) NOT NULL,
  `domaine_client` varchar(50) NOT NULL,
  `numero_client` varchar(50) NOT NULL,
  `email_client` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Contenu de la table `client`
--

INSERT INTO `client` (`id_client`, `societe_client`, `adresse_client`, `domaine_client`, `numero_client`, `email_client`) VALUES
(1, 'UHA', '2 rue des Frères Lumières, 68100 Mulhouse', 'Université', '0389336000', 'uha@uha.fr'),
(2, 'Chaiseland', '129 rue de Belfort, 68100 Mulhouse', 'Ebénisterie', '0389604637', 'chaiseland@uha.fr'),
(3, 'FNAC', '54 rue du Sauvage, 68100 Mulhouse', 'Magasin', '0825020020', 'fnac@uha.fr');

-- --------------------------------------------------------

--
-- Structure de la table `commande`
--

CREATE TABLE `commande` (
  `id_commande` int(11) UNSIGNED NOT NULL,
  `ce_client` int(11) UNSIGNED NOT NULL,
  `date_commande` date NOT NULL,
  `date_limite_livraison` date NOT NULL,
  `date_livraison` date NOT NULL,
  `prix_total` decimal(11,2) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Structure de la table `commande_article`
--

CREATE TABLE `commande_article` (
  `id_commande_article` int(11) UNSIGNED NOT NULL,
  `ce_commande` int(11) UNSIGNED NOT NULL,
  `ce_produit` int(11) UNSIGNED NOT NULL,
  `quantite` int(11) UNSIGNED NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Structure de la table `commande_fournisseur`
--

CREATE TABLE `commande_fournisseur` (
  `id_commande_fournisseur` int(11) UNSIGNED NOT NULL,
  `ce_produit` int(11) UNSIGNED NOT NULL,
  `date_commande` date NOT NULL,
  `quantite` int(11) UNSIGNED NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Structure de la table `commande_temporaire`
--

CREATE TABLE `commande_temporaire` (
  `id_commande_temporaire` int(11) UNSIGNED NOT NULL,
  `nom_societe` varchar(50) NOT NULL,
  `nom_produit` varchar(50) NOT NULL,
  `prix_unitaire` decimal(11,2) NOT NULL,
  `quantite` int(11) UNSIGNED NOT NULL,
  `total` decimal(11,2) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Structure de la table `fournisseur`
--

CREATE TABLE `fournisseur` (
  `id_fournisseur` int(11) UNSIGNED NOT NULL,
  `societe_fournisseur` varchar(50) NOT NULL,
  `adresse_fournisseur` varchar(50) NOT NULL,
  `domaine_fournisseur` varchar(50) NOT NULL,
  `numero_fournisseur` varchar(50) NOT NULL,
  `email_fournisseur` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Contenu de la table `fournisseur`
--

INSERT INTO `fournisseur` (`id_fournisseur`, `societe_fournisseur`, `adresse_fournisseur`, `domaine_fournisseur`, `numero_fournisseur`, `email_fournisseur`) VALUES
(1, 'Cora', '258 rue de Belfort, 68067', 'Supermarché', '0389323838', 'cora@uha.fr'),
(2, 'Leclerc', '7 rue Gay Lussac, 68200', 'Supermarché', '0389354900', 'leclerc@uha.fr'),
(3, 'Eureka', '52 rue de Dornach, 68120', 'Informatique', '0389512020', 'eureka@uha.fr'),
(4, 'Ikea', 'ZAC Parc des collines, 68790 Morschwiller-le-Bas', 'Mobilier', '0969362006', 'ikea@uha.fr'),
(5, 'Atlantis', '43 rue Buffon, 68200 Mulhouse', 'Informatique', '0389334334', 'atlantis@uha.fr');

-- --------------------------------------------------------

--
-- Structure de la table `produit`
--

CREATE TABLE `produit` (
  `id_produit` int(11) UNSIGNED NOT NULL,
  `nom_produit` varchar(50) NOT NULL,
  `prix_unitaire` decimal(11,2) NOT NULL,
  `quantite` int(11) NOT NULL,
  `date_expiration` date NOT NULL,
  `societe_fournisseur` varchar(50) NOT NULL,
  `ce_fournisseur` int(11) UNSIGNED NOT NULL,
  `stock_minimal` int(11) UNSIGNED NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Contenu de la table `produit`
--

INSERT INTO `produit` (`id_produit`, `nom_produit`, `prix_unitaire`, `quantite`, `date_expiration`, `societe_fournisseur`, `ce_fournisseur`, `stock_minimal`) VALUES
(1, 'Aspirateur', '61.30', 50, '2017-03-31', 'Cora', 1, 10),
(2, 'ERP', '1000000.00', 10, '2017-03-28', 'Eureka', 3, 3),
(3, 'Aspirateur', '59.50', 90, '2017-03-16', 'Leclerc', 2, 15),
(4, 'Ordinateur', '1550.00', 30, '2017-03-22', 'Atlantis', 5, 5),
(5, 'Ecran', '225.00', 300, '2017-03-08', 'Atlantis', 5, 20),
(6, 'Lit', '450.25', 500, '2017-03-16', 'Ikea', 4, 80);

-- --------------------------------------------------------

--
-- Structure de la table `role`
--

CREATE TABLE `role` (
  `id_role` int(11) UNSIGNED NOT NULL,
  `nom_role` varchar(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

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

CREATE TABLE `utilisateur` (
  `id_utilisateur` int(11) UNSIGNED NOT NULL,
  `nom_utilisateur` varchar(50) NOT NULL,
  `prenom_utilisateur` varchar(50) NOT NULL,
  `email_utilisateur` varchar(50) NOT NULL,
  `password_utilisateur` varchar(50) NOT NULL,
  `date_naissance` date NOT NULL,
  `date_embauche` date NOT NULL,
  `adresse_utilisateur` varchar(100) NOT NULL,
  `ce_role` int(11) UNSIGNED NOT NULL,
  `numero_utilisateur` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Contenu de la table `utilisateur`
--

INSERT INTO `utilisateur` (`id_utilisateur`, `nom_utilisateur`, `prenom_utilisateur`, `email_utilisateur`, `password_utilisateur`, `date_naissance`, `date_embauche`, `adresse_utilisateur`, `ce_role`, `numero_utilisateur`) VALUES
(1, 'DUPONT', 'Jean', 'admin@uha.fr', 'admin', '1994-05-30', '2016-10-10', '6 rue des champs, 68200 Mulhouse', 1, '0389010203'),
(2, 'MARTIN', 'Arnaud', 'utilisateur@uha.fr', 'utilisateur', '1988-02-10', '2016-04-19', '17 rue du Soleil, 67000 Strasbourg', 2, '0389070809');

--
-- Index pour les tables exportées
--

--
-- Index pour la table `client`
--
ALTER TABLE `client`
  ADD PRIMARY KEY (`id_client`);

--
-- Index pour la table `commande`
--
ALTER TABLE `commande`
  ADD PRIMARY KEY (`id_commande`),
  ADD KEY `ce_client` (`ce_client`);

--
-- Index pour la table `commande_article`
--
ALTER TABLE `commande_article`
  ADD PRIMARY KEY (`id_commande_article`),
  ADD KEY `ce_commande` (`ce_commande`),
  ADD KEY `ce_produit` (`ce_produit`);

--
-- Index pour la table `commande_fournisseur`
--
ALTER TABLE `commande_fournisseur`
  ADD PRIMARY KEY (`id_commande_fournisseur`),
  ADD KEY `ce_produit` (`ce_produit`);

--
-- Index pour la table `commande_temporaire`
--
ALTER TABLE `commande_temporaire`
  ADD PRIMARY KEY (`id_commande_temporaire`);

--
-- Index pour la table `fournisseur`
--
ALTER TABLE `fournisseur`
  ADD PRIMARY KEY (`id_fournisseur`);

--
-- Index pour la table `produit`
--
ALTER TABLE `produit`
  ADD PRIMARY KEY (`id_produit`),
  ADD KEY `ce_fournisseur` (`ce_fournisseur`);

--
-- Index pour la table `role`
--
ALTER TABLE `role`
  ADD PRIMARY KEY (`id_role`);

--
-- Index pour la table `utilisateur`
--
ALTER TABLE `utilisateur`
  ADD PRIMARY KEY (`id_utilisateur`),
  ADD KEY `ce_role` (`ce_role`);

--
-- AUTO_INCREMENT pour les tables exportées
--

--
-- AUTO_INCREMENT pour la table `client`
--
ALTER TABLE `client`
  MODIFY `id_client` int(11) UNSIGNED NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;
--
-- AUTO_INCREMENT pour la table `commande`
--
ALTER TABLE `commande`
  MODIFY `id_commande` int(11) UNSIGNED NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT pour la table `commande_article`
--
ALTER TABLE `commande_article`
  MODIFY `id_commande_article` int(11) UNSIGNED NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT pour la table `commande_fournisseur`
--
ALTER TABLE `commande_fournisseur`
  MODIFY `id_commande_fournisseur` int(11) UNSIGNED NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT pour la table `commande_temporaire`
--
ALTER TABLE `commande_temporaire`
  MODIFY `id_commande_temporaire` int(11) UNSIGNED NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT pour la table `fournisseur`
--
ALTER TABLE `fournisseur`
  MODIFY `id_fournisseur` int(11) UNSIGNED NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;
--
-- AUTO_INCREMENT pour la table `produit`
--
ALTER TABLE `produit`
  MODIFY `id_produit` int(11) UNSIGNED NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=7;
--
-- AUTO_INCREMENT pour la table `role`
--
ALTER TABLE `role`
  MODIFY `id_role` int(11) UNSIGNED NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;
--
-- AUTO_INCREMENT pour la table `utilisateur`
--
ALTER TABLE `utilisateur`
  MODIFY `id_utilisateur` int(11) UNSIGNED NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;
--
-- Contraintes pour les tables exportées
--

--
-- Contraintes pour la table `commande`
--
ALTER TABLE `commande`
  ADD CONSTRAINT `commande_ibfk_1` FOREIGN KEY (`ce_client`) REFERENCES `client` (`id_client`);

--
-- Contraintes pour la table `commande_article`
--
ALTER TABLE `commande_article`
  ADD CONSTRAINT `commande_article_ibfk_1` FOREIGN KEY (`ce_commande`) REFERENCES `commande` (`id_commande`),
  ADD CONSTRAINT `commande_article_ibfk_2` FOREIGN KEY (`ce_produit`) REFERENCES `produit` (`id_produit`);

--
-- Contraintes pour la table `commande_fournisseur`
--
ALTER TABLE `commande_fournisseur`
  ADD CONSTRAINT `commande_fournisseur_ibfk_1` FOREIGN KEY (`ce_produit`) REFERENCES `produit` (`id_produit`);

--
-- Contraintes pour la table `produit`
--
ALTER TABLE `produit`
  ADD CONSTRAINT `produit_ibfk_1` FOREIGN KEY (`ce_fournisseur`) REFERENCES `fournisseur` (`id_fournisseur`);

--
-- Contraintes pour la table `utilisateur`
--
ALTER TABLE `utilisateur`
  ADD CONSTRAINT `utilisateur_ibfk_1` FOREIGN KEY (`ce_role`) REFERENCES `role` (`id_role`);

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
