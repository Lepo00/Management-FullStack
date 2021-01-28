DELETE FROM item WHERE id=1;
DELETE FROM item WHERE id=2;
DELETE FROM item WHERE id=3;
DELETE FROM item WHERE id=4;
DELETE FROM item WHERE id=5;
DELETE FROM item WHERE id=6;
DELETE FROM item WHERE id=7;
DELETE FROM item WHERE id=8;
DELETE FROM item WHERE id=9;
DELETE FROM item WHERE id=10;
DELETE FROM item WHERE id=11;
DELETE FROM item WHERE id=12;
DELETE FROM item WHERE id=13;
DELETE FROM item WHERE id=14;
DELETE FROM item WHERE id=15;

DELETE FROM unit_of_measure WHERE id=1;
DELETE FROM unit_of_measure WHERE id=2;
DELETE FROM unit_of_measure WHERE id=3;
DELETE FROM unit_of_measure WHERE id=4;
DELETE FROM unit_of_measure WHERE id=5;
DELETE FROM unit_of_measure WHERE id=6;

INSERT INTO `unit_of_measure` (`id`, `unit`, `name`) VALUES
(1, 'g', 'grammi'),
(2, 'kg', 'chilogrammi'),
(3, 'ml', 'millilitri'),
(4, 'l', 'litri'),
(5, 'm', 'metri'),
(6, 'km', 'chilometri');

INSERT INTO `item` (`id`, `created_at`, `updated_at`, `code`, `description`, `price`, `quantity`, `unit_of_measure_id`) VALUES 
(1, CURRENT_TIME(), CURRENT_TIME(), 'AAA', 'legno', '10', NULL, 2),
(2, CURRENT_TIME(), CURRENT_TIME(), 'BBB', 'roccia', '20', NULL, 2),
(3, CURRENT_TIME(), CURRENT_TIME(), 'CCC', 'acqua', '30', NULL, 4),
(4, CURRENT_TIME(), CURRENT_TIME(), 'DDD', 'pietra', '40', NULL, 2),
(5, CURRENT_TIME(), CURRENT_TIME(), 'EEE', 'fango', '50', NULL, 2),
(6, CURRENT_TIME(), CURRENT_TIME(), 'FFF', 'sabbia', '60', NULL, 2),
(7, CURRENT_TIME(), CURRENT_TIME(), 'GGG', 'veleno', '70', NULL, 3),
(8, CURRENT_TIME(), CURRENT_TIME(), 'HHH', 'rete', '80', NULL, 5),
(9, CURRENT_TIME(), CURRENT_TIME(), 'III', 'bronzo', '90', NULL, 1),
(10, CURRENT_TIME(), CURRENT_TIME(), 'JJJ', 'oro', '100', NULL, 1),
(11, CURRENT_TIME(), CURRENT_TIME(), 'KKK', 'spago', '110', NULL, 5),
(12, CURRENT_TIME(), CURRENT_TIME(), 'LLL', 'cavo', '120', NULL, 6),
(13, CURRENT_TIME(), CURRENT_TIME(), 'MMM', 'granito', '130', NULL, 5),
(14, CURRENT_TIME(), CURRENT_TIME(), 'NNN', 'marmo', '140', NULL, 1),
(15, CURRENT_TIME(), CURRENT_TIME(), 'OOO', 'diluente', '150', NULL, 4)