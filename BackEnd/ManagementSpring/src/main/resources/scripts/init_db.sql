--INSERT INTO `unit_of_measure` (`id`, `unit`, `name`) VALUES
--(1, 'g', 'grammi'),
--(2, 'kg', 'chilogrammi'),
--(3, 'ml', 'millilitri'),
--(4, 'l', 'litri'),
--(5, 'm', 'metri'),
--(6, 'km', 'chilometri');

--INSERT INTO `item` (`id`, `created_at`, `updated_at`, `code`, `description`, `price`, `unit_of_measure_id`) VALUES 
---(1, CURRENT_TIME(), CURRENT_TIME(), 'AAA', 'legno', '10',  2),
--(2, CURRENT_TIME(), CURRENT_TIME(), 'BBB', 'roccia', '20',  2),
--(3, CURRENT_TIME(), CURRENT_TIME(), 'CCC', 'acqua', '30',  4),
--(4, CURRENT_TIME(), CURRENT_TIME(), 'DDD', 'pietra', '40',  2),
--(5, CURRENT_TIME(), CURRENT_TIME(), 'EEE', 'fango', '50',  2),
--(6, CURRENT_TIME(), CURRENT_TIME(), 'FFF', 'sabbia', '60',  2),
--(7, CURRENT_TIME(), CURRENT_TIME(), 'GGG', 'veleno', '70',  3),
--(8, CURRENT_TIME(), CURRENT_TIME(), 'HHH', 'rete', '80',  5),
--(9, CURRENT_TIME(), CURRENT_TIME(), 'III', 'bronzo', '90',  1),
--(10, CURRENT_TIME(), CURRENT_TIME(), 'JJJ', 'oro', '100',  1),
--(11, CURRENT_TIME(), CURRENT_TIME(), 'KKK', 'spago', '110',  5),
--(12, CURRENT_TIME(), CURRENT_TIME(), 'LLL', 'cavo', '120',  6),
--(13, CURRENT_TIME(), CURRENT_TIME(), 'MMM', 'granito', '130',  5),
--(14, CURRENT_TIME(), CURRENT_TIME(), 'NNN', 'marmo', '140',  1),
--(15, CURRENT_TIME(), CURRENT_TIME(), 'OOO', 'diluente', '150',  4)

INSERT INTO `user` (`id`, `created_at`, `updated_at`, `address`, `iva_code`, `password`, `phone`, `username`)
SELECT * FROM
(SELECT '1' `id`, CURRENT_TIME() `created_at`, CURRENT_TIME() `updated_at`, 'Milano, Via A. De Gasperi 32' `address`, 'iva_code_example_1' `iva_code`, '123' `password`, '3293456156' `phone`, 'luca' `username` UNION ALL
SELECT '2' `id`, CURRENT_TIME() `created_at`, CURRENT_TIME() `updated_at`, 'Milano, Via A. De Gasperi 32' `address`, 'iva_code_example_2' `iva_code`, '123' `password`, '3293456156' `phone`, 'alessandro' `username` UNION ALL
SELECT '3' `id`, CURRENT_TIME() `created_at`, CURRENT_TIME() `updated_at`, 'Milano, Via A. De Gasperi 32' `address`, 'iva_code_example_3' `iva_code`, '123' `password`, '3293456156' `phone`, 'andrea' `username` UNION ALL
SELECT '4' `id`, CURRENT_TIME() `created_at`, CURRENT_TIME() `updated_at`, 'Milano, Via A. De Gasperi 32' `address`, 'iva_code_example_4' `iva_code`, '123' `password`, '3293456156' `phone`, 'amanpreet' `username` UNION ALL
SELECT '5' `id`, CURRENT_TIME() `created_at`, CURRENT_TIME() `updated_at`, 'Milano, Via A. De Gasperi 32' `address`, 'iva_code_example_5' `iva_code`, '123' `password`, '3293456156' `phone`, 'matteo' `username` UNION ALL
SELECT '6' `id`, CURRENT_TIME() `created_at`, CURRENT_TIME() `updated_at`, 'Milano, Via A. De Gasperi 32' `address`, 'iva_code_example_6' `iva_code`, '123' `password`, '3293456156' `phone`, 'prova' `username`) A
WHERE NOT EXISTS (SELECT NULL FROM unit_of_measure B WHERE A.id=B.id);

INSERT INTO `unit_of_measure`(`id`, `unit`, `name`)
SELECT * FROM
(SELECT '1' `id`, 'g' `unit`, 'grammi' `name` UNION ALL
 SELECT '2' `id`, 'kg' `unit`, 'chilogrammi' `name` UNION ALL
 SELECT '3' `id`, 'ml' `unit`, 'millilitri' `name` UNION ALL
 SELECT '4' `id`, 'l' `unit`, 'litri' `name` UNION ALL
 SELECT '5' `id`, 'm' `unit`, 'metri' `name` UNION ALL
 SELECT '6' `id`, 'km' `unit`, 'chilometri' `name` ) A
WHERE NOT EXISTS (SELECT NULL FROM unit_of_measure B WHERE A.id=B.id);

INSERT INTO `item` (`id`, `created_at`, `updated_at`, `code`, `description`, `price`, `unit_of_measure_id`)
SELECT * FROM
(SELECT '1' `id`, CURRENT_TIME() `created_at`, CURRENT_TIME() `updated_at`, 'AAA' `code`, 'legno' `description`, 10 `price`, 2 `unit_of_measure_id` UNION ALL
SELECT '2' `id`, CURRENT_TIME() `created_at`, CURRENT_TIME() `updated_at`, 'BBB' `code`, 'roccia' `description`, 20 `price`, 2 `unit_of_measure_id` UNION ALL
SELECT '3' `id`, CURRENT_TIME() `created_at`, CURRENT_TIME() `updated_at`, 'CCC' `code`, 'acqua' `description`, 30 `price`, 4 `unit_of_measure_id` UNION ALL
SELECT '4' `id`, CURRENT_TIME() `created_at`, CURRENT_TIME() `updated_at`, 'DDD' `code`, 'pietra' `description`, 40 `price`, 2 `unit_of_measure_id` UNION ALL
SELECT '5' `id`, CURRENT_TIME() `created_at`, CURRENT_TIME() `updated_at`, 'EEE' `code`, 'fango' `description`, 50 `price`, 2 `unit_of_measure_id` UNION ALL
SELECT '6' `id`, CURRENT_TIME() `created_at`, CURRENT_TIME() `updated_at`, 'FFF' `code`, 'sabbia' `description`, 60 `price`, 2 `unit_of_measure_id` UNION ALL
SELECT '7' `id`, CURRENT_TIME() `created_at`, CURRENT_TIME() `updated_at`, 'GGG' `code`, 'ghiaia' `description`, 70 `price`, 3 `unit_of_measure_id` UNION ALL
SELECT '8' `id`, CURRENT_TIME() `created_at`, CURRENT_TIME() `updated_at`, 'HHH' `code`, 'rete' `description`, 80 `price`, 5 `unit_of_measure_id` UNION ALL
SELECT '9' `id`, CURRENT_TIME() `created_at`, CURRENT_TIME() `updated_at`, 'III' `code`, 'bronzo' `description`, 90 `price`, 1 `unit_of_measure_id` UNION ALL
SELECT '10' `id`, CURRENT_TIME() `created_at`, CURRENT_TIME() `updated_at`, 'JJJ' `code`, 'oro' `description`, 100 `price`, 1 `unit_of_measure_id` UNION ALL
SELECT '11' `id`, CURRENT_TIME() `created_at`, CURRENT_TIME() `updated_at`, 'KKK' `code`, 'spago' `description`, 110 `price`, 5 `unit_of_measure_id` UNION ALL
SELECT '12' `id`, CURRENT_TIME() `created_at`, CURRENT_TIME() `updated_at`, 'LLL' `code`, 'argento' `description`, 120 `price`, 6 `unit_of_measure_id` UNION ALL
SELECT '13' `id`, CURRENT_TIME() `created_at`, CURRENT_TIME() `updated_at`, 'MMM' `code`, 'granito' `description`, 130 `price`, 5 `unit_of_measure_id` UNION ALL
SELECT '14' `id`, CURRENT_TIME() `created_at`, CURRENT_TIME() `updated_at`, 'NNN' `code`, 'marmo' `description`, 140 `price`, 1 `unit_of_measure_id` UNION ALL
SELECT '15' `id`, CURRENT_TIME() `created_at`, CURRENT_TIME() `updated_at`, 'OOO' `code`, 'sciroppo' `description`, 150 `price`, 4 `unit_of_measure_id`) A
WHERE NOT EXISTS (SELECT NULL FROM item B WHERE A.id=B.id);
