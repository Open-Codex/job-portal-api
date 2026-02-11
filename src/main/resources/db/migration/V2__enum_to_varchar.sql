-- ============================================================
-- MIGRATION: Convert PostgreSQL ENUM columns to VARCHAR
-- Reason: Better compatibility with JPA / Hibernate
-- ============================================================

-- JOB_OFFERS ENUM COLUMNS
ALTER TABLE job_offers
ALTER COLUMN contract_type TYPE VARCHAR(50)
USING contract_type::text;

ALTER TABLE job_offers
ALTER COLUMN salary_type TYPE VARCHAR(50)
USING salary_type::text;

ALTER TABLE job_offers
ALTER COLUMN seniority TYPE VARCHAR(50)
USING seniority::text;

ALTER TABLE job_offers
ALTER COLUMN location_type TYPE VARCHAR(50)
USING location_type::text;