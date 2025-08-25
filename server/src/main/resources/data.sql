insert into asset (NAME, TYPE)
values
    ('Gold', 'commodity'),
    ('Silver', 'commodity'),
    ('Appl', 'stock');

insert into transaction (DATE, TYPE, ASSET, QUANTITY, PRICE_PER_UNIT_INUSD)
values
    ('23-12-2024', 'BUY', 1, 100, 1250),
    ('12-09-2025', 'SELL', 2, 50, 560);