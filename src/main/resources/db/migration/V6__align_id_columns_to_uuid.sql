DO $$
BEGIN
    IF EXISTS (
        SELECT 1
        FROM information_schema.columns
        WHERE table_schema = 'public'
          AND table_name = 'users'
          AND column_name = 'id'
          AND udt_name <> 'uuid'
    ) THEN
        EXECUTE 'ALTER TABLE public.users ALTER COLUMN id TYPE UUID USING id::uuid';
    END IF;
END $$;

DO $$
BEGIN
    IF EXISTS (
        SELECT 1
        FROM information_schema.columns
        WHERE table_schema = 'public'
          AND table_name = 'tasks'
          AND column_name = 'id'
          AND udt_name <> 'uuid'
    ) THEN
        EXECUTE 'ALTER TABLE public.tasks ALTER COLUMN id TYPE UUID USING id::uuid';
    END IF;
END $$;

DO $$
BEGIN
    IF EXISTS (
        SELECT 1
        FROM information_schema.columns
        WHERE table_schema = 'tenant_1'
          AND table_name = 'users'
          AND column_name = 'id'
          AND udt_name <> 'uuid'
    ) THEN
        EXECUTE 'ALTER TABLE tenant_1.users ALTER COLUMN id TYPE UUID USING id::uuid';
    END IF;
END $$;

DO $$
BEGIN
    IF EXISTS (
        SELECT 1
        FROM information_schema.columns
        WHERE table_schema = 'tenant_1'
          AND table_name = 'tasks'
          AND column_name = 'id'
          AND udt_name <> 'uuid'
    ) THEN
        EXECUTE 'ALTER TABLE tenant_1.tasks ALTER COLUMN id TYPE UUID USING id::uuid';
    END IF;
END $$;

DO $$
BEGIN
    IF EXISTS (
        SELECT 1
        FROM information_schema.columns
        WHERE table_schema = 'tenant_2'
          AND table_name = 'users'
          AND column_name = 'id'
          AND udt_name <> 'uuid'
    ) THEN
        EXECUTE 'ALTER TABLE tenant_2.users ALTER COLUMN id TYPE UUID USING id::uuid';
    END IF;
END $$;

DO $$
BEGIN
    IF EXISTS (
        SELECT 1
        FROM information_schema.columns
        WHERE table_schema = 'tenant_2'
          AND table_name = 'tasks'
          AND column_name = 'id'
          AND udt_name <> 'uuid'
    ) THEN
        EXECUTE 'ALTER TABLE tenant_2.tasks ALTER COLUMN id TYPE UUID USING id::uuid';
    END IF;
END $$;
