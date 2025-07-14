# Integration Library

## Documentation

Confluence: https://mehrwerk.atlassian.net/wiki/spaces/MWE/pages/1235976250/Account+Service+Documentation

## General technical information

Some basic configuration properties are already set in the core-library to make it more easy to setup a new
microservice.
Your service application.yml will inherit the application.yml settings from the core-library.
The inheritance also works when dealing with profile based application properties like
``application-dev.yml`` which would inherit in this case from your service ``application.yml``.

## Setup for development environment

1. By default the dev profile is specified in the ``application.yml`` with ``spring.active.profiles = dev``.
   You can override the profile in intellij in the run configuration for your personal need.
2. ``octopus.service.uuid`` should be set to the microservice uuid.
3. ``octopus.service.client-id`` and ``octopus.service.client-secret`` are required for service-to-service
   communication. For example to fetch the database configurations for tenant based services.
4. For tenant based services setup is:
    1. ``octopus.datasource.tenant-schema-enabled = true``
    2. ``octopus.datasource.dbo-schema-enabled = false``
5. For dbo (non tenant) based services setup is:
    1. ``octopus.datasource.tenant-schema-enabled = false``
    2. ``octopus.datasource.dbo-schema-enabled = true``
    3. ``octopus.datasource.host/database/username`` connection settings for dbo schema. In general this is your local
       dev database
    4. You can also use the dbo schema in your dev environment
       with a tenant based service to run a specific tenant on the dbo schema on your local database.
6. In the 'dev' environment you can specify whether to drop, migrate or create database tables on application start.
   See ```octopus.database-populator```. Be careful with this settings in other environments than 'dev'.
   The folder of the migration scripts can be specified with:
    1. ```octopus.database-populator.flyway-tenant-schema-script-location``` when tenant schema is enabled
    2. ```octopus.database-populator.flyway-default-schema-script-location``` when dbo schema is enabled
7. Service URLs
    1. ```octopus.service-urls.tenant-service-url``` required for fetching database configs for tenants
    2. ```octopus.service-urls.iam-service-url``` required for getting a token for a service user.
    3. Additional service urls can be specified here as well if necessary.
