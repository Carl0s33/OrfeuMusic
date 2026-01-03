-- Add status column to artista and album tables
ALTER TABLE artista
    ADD COLUMN status VARCHAR(20) DEFAULT 'PENDING';

ALTER TABLE album
    ADD COLUMN status VARCHAR(20) DEFAULT 'PENDING';

-- Update existing records to APPROVED (optional, but good for existing data)
UPDATE artista SET status = 'APPROVED' WHERE status IS NULL;
UPDATE album SET status = 'APPROVED' WHERE status IS NULL;

