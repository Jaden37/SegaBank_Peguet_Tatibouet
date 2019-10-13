-- phpMyAdmin SQL Dump
-- version 4.8.5
-- https://www.phpmyadmin.net/
--
-- Hôte : 127.0.0.1:3307
-- Généré le :  Dim 13 oct. 2019 à 15:20
-- Version du serveur :  10.3.14-MariaDB
-- Version de PHP :  7.2.18

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de données :  `segabank`
--

-- --------------------------------------------------------

--
-- Structure de la table `agence`
--

DROP TABLE IF EXISTS `agence`;
CREATE TABLE IF NOT EXISTS `agence` (
  `idAgence` int(11) NOT NULL AUTO_INCREMENT,
  `code` varchar(20) NOT NULL,
  `adresse` varchar(255) NOT NULL,
  PRIMARY KEY (`idAgence`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

--
-- Déchargement des données de la table `agence`
--

INSERT INTO `agence` (`idAgence`, `code`, `adresse`) VALUES
(1, 'CA du Morbihan', '5 rue du crack 44200 NANTES'),
(2, 'Caisse d\'Epargne', '16 bd du général de Gaulle 44000 NANTES');

-- --------------------------------------------------------

--
-- Structure de la table `compte`
--

DROP TABLE IF EXISTS `compte`;
CREATE TABLE IF NOT EXISTS `compte` (
  `idCompte` int(11) NOT NULL AUTO_INCREMENT,
  `solde` double NOT NULL,
  `decouvert` double DEFAULT NULL,
  `tauxInteret` float DEFAULT NULL,
  `type` varchar(1) NOT NULL,
  `idAgence` int(11) NOT NULL,
  PRIMARY KEY (`idCompte`),
  KEY `idAgence` (`idAgence`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;

--
-- Déchargement des données de la table `compte`
--

INSERT INTO `compte` (`idCompte`, `solde`, `decouvert`, `tauxInteret`, `type`, `idAgence`) VALUES
(1, 5000, 400, NULL, 'S', 1),
(2, 5500, NULL, 10, 'E', 2),
(3, 41989.5, NULL, NULL, 'P', 1),
(4, 400, 300, NULL, 'S', 2),
(5, 6000, NULL, 20, 'E', 2),
(6, 800, NULL, NULL, 'P', 2);

-- --------------------------------------------------------

--
-- Structure de la table `operations`
--

DROP TABLE IF EXISTS `operations`;
CREATE TABLE IF NOT EXISTS `operations` (
  `idOperation` int(11) NOT NULL AUTO_INCREMENT,
  `Nom` varchar(255) NOT NULL,
  `Montant` float NOT NULL,
  `dateHeure` timestamp NOT NULL DEFAULT current_timestamp() ON UPDATE current_timestamp(),
  `idCompte` int(11) NOT NULL,
  PRIMARY KEY (`idOperation`),
  KEY `idCompte` (`idCompte`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8;

--
-- Déchargement des données de la table `operations`
--

INSERT INTO `operations` (`idOperation`, `Nom`, `Montant`, `dateHeure`, `idCompte`) VALUES
(1, 'Creation du compte simple', 5000, '2019-10-13 14:43:38', 1),
(2, 'Creation du compte epargne', 5000, '2019-10-13 14:44:58', 2),
(3, 'Creation du compte payant', 42000, '2019-10-13 14:45:33', 3),
(4, 'Creation du compte simple', 500, '2019-10-13 14:47:36', 4),
(5, 'Creation du compte epargne', 6000, '2019-10-13 14:47:57', 5),
(6, 'Creation du compte payant', 800, '2019-10-13 14:48:54', 6),
(7, 'Retrait du compte simple', -100, '2019-10-13 14:50:00', 4),
(8, 'Versement du compte epargne', 500, '2019-10-13 14:51:22', 2),
(9, 'Retrait du compte payant', -10.5, '2019-10-13 14:53:13', 3);

--
-- Contraintes pour les tables déchargées
--

--
-- Contraintes pour la table `compte`
--
ALTER TABLE `compte`
  ADD CONSTRAINT `compte_ibfk_1` FOREIGN KEY (`idAgence`) REFERENCES `agence` (`idAgence`);

--
-- Contraintes pour la table `operations`
--
ALTER TABLE `operations`
  ADD CONSTRAINT `operations_ibfk_1` FOREIGN KEY (`idCompte`) REFERENCES `compte` (`idCompte`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
