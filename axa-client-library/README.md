# Integration Library

## Documentation

Documentation is available in confluence however it is not complete since the information about where and which cases it
will be used is not yet determined.
Confluence: https://mehrwerk.atlassian.net/wiki/spaces/MWE/pages/1148289136/AXA

## General technical information

This client-library is for axa integrations. Authentication is done automatically via interceptor.
There are unit tests for models and request and response. Also sample application with one controller to test the api
without linking library to another project.

Application relies on environment variables for configuration. This is done to make it easier to use in different
environments.
Each environment is in separate file. All of them are provided in confluence documentation for the tokenization and
usage. There are some added to make api integration seamless.

## Environment variables

Following environment variables are used for configuration of the AXA client library and to make requests to AXA API:
Username: Username for AXA API
Password: Password for AXA API
Token endpoint: Endpoint for getting token from AXA API
Auth base url: Base URL for AXA API authentication
API base url: Base URL for AXA API to make requests
X-Environment: Environment for AXA API
X-Tenant-ID: Tenant ID for AXA API

Following environment variables are used for configuration of the AXA client library work more effective which are not
in confluence page and not mentioned.

Token-refresh-buffer: Buffer time for token refresh in minutes. Currently set to 1 hour. This is the token expiration
time on AXA side.
Connect-timeout: Timeout for connection in seconds. Currently set to 30 seconds. Reason is axa taking 15-20 seconds to
respond.
Read-timeout: Timeout for reading response in seconds. Currently set to 20 seconds. Reason is axa taking 15-20 seconds
to respond.