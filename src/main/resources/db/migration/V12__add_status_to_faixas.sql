-- Add status column to faixas table
ALTER TABLE faixas
    ADD COLUMN status VARCHAR(20) DEFAULT 'PENDING';

UPDATE faixas SET status = 'APPROVED' WHERE status IS NULL;

