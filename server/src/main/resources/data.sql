insert into asset (NAME, TYPE, QUANTITY)
values
    ('GOOGL', 'stock', 230),
    ('MSFT', 'stock', 45.5),
    ('AAPL', 'stock', 333.333);

insert into transaction (DATE, TYPE, ASSET, QUANTITY, PRICE_PER_UNIT)
values
    ('2024-12-23', 'BuY', 1, 100, 1250),
    ('2025-09-12', 'SELl', 2, 50, 560);