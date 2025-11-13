-- ============================================================
-- EXTENSION FOR RANDOM UUID
-- ============================================================
CREATE EXTENSION IF NOT EXISTS "pgcrypto";

-- ============================================================
-- ENUMS
-- ============================================================

CREATE TYPE role_enum AS ENUM ('USER', 'ADMIN');

CREATE TYPE salary_type_enum AS ENUM ('MONTHLY', 'YEARLY', 'HOURLY', 'NONE');

CREATE TYPE contract_type_enum AS ENUM ('FULL_TIME', 'PART_TIME', 'FREELANCE', 'INTERNSHIP', 'TEMPORARY');

CREATE TYPE seniority_enum AS ENUM ('TRAINEE', 'JUNIOR', 'MID', 'SENIOR', 'LEAD');

CREATE TYPE location_type_enum AS ENUM ('REMOTE', 'HYBRID', 'ONSITE');

-- ============================================================
-- USERS TABLE
-- ============================================================

CREATE TABLE users (
    id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    name VARCHAR(100) NOT NULL,
    email VARCHAR(100) NOT NULL UNIQUE,
    password VARCHAR(255) NOT NULL,
    role role_enum DEFAULT 'USER' NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

-- ============================================================
-- SKILLS TABLE
-- ============================================================

CREATE TABLE skills (
    id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    name VARCHAR(100) NOT NULL UNIQUE
);

-- ============================================================
-- CATEGORIES TABLE
-- ============================================================

CREATE TABLE categories (
    id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    name VARCHAR(100) NOT NULL UNIQUE
);

-- ============================================================
-- COUNTRIES TABLE
-- ============================================================

CREATE TABLE countries (
    id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    name VARCHAR(100) NOT NULL UNIQUE
);

-- ============================================================
-- LANGUAGES TABLE
-- ============================================================

CREATE TABLE languages (
    id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    code VARCHAR(10) NOT NULL UNIQUE,   -- 'es', 'en'
    name VARCHAR(100) NOT NULL
);

-- ============================================================
-- JOB OFFERS TABLE
-- ============================================================

CREATE TABLE job_offers (
    id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    title VARCHAR(150) NOT NULL,
    description TEXT NOT NULL,
    salary NUMERIC(12,2),
    salary_type salary_type_enum DEFAULT 'NONE',
    company_name VARCHAR(150),
    company_image_url TEXT,
    application_link TEXT,
    application_email VARCHAR(150),
    contract_type contract_type_enum,
    seniority seniority_enum,
    location_type location_type_enum,
    active BOOLEAN DEFAULT TRUE,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    user_id UUID NOT NULL REFERENCES users(id) ON DELETE CASCADE,

    -- FOREIGN KEYS
    category_id UUID REFERENCES categories(id) ON DELETE SET NULL,
    country_id UUID REFERENCES countries(id) ON DELETE SET NULL,
    language_id UUID REFERENCES languages(id) ON DELETE SET NULL
);

-- ============================================================
-- RELATION TABLE (MANY-TO-MANY JOB_OFFERS <-> SKILLS)
-- ============================================================

CREATE TABLE job_offer_skills (
    job_offer_id UUID NOT NULL REFERENCES job_offers(id) ON DELETE CASCADE,
    skill_id UUID NOT NULL REFERENCES skills(id) ON DELETE CASCADE,
    PRIMARY KEY (job_offer_id, skill_id)
);