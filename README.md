# Inventory-Number-Management

# Software Requirements Specification (SRS) Document

## Project Information

- **Author/Owner:** Vignesh Ethiraj
- **Project Name:** Wireless Inventory Number Management
- **Description:** Build an application to handle number management for wireless network management.

## Project Functional Requirements

The Wireless Inventory Number Management application aims to provide a comprehensive solution for managing ICCID, MSISDN, and IMEI numbers used in wireless network management. The application will offer both API and UI interfaces to fulfill the following functional requirements:

### Number Reservation and Tracking

#### ICCID Management:

- Reserve ICCID numbers.
- Release reserved ICCID numbers.
- Maintain a pool of available ICCID numbers.
- Associate ICCID numbers with customer records.

#### MSISDN Management:

- Reserve individual MSISDN numbers.
- Release reserved MSISDN numbers.
- Reserve a range of MSISDN numbers.
- Associate MSISDN numbers with customer records.
- Revert reserved MSISDN numbers back to the pool after a specified period.

#### IMEI Management:

- Allocate IMEI numbers when a SIM is allocated to a customer.
- Maintain associations between IMEI, ICCID, and customer records.

### User Interfaces

#### API Interfaces:

- Provide RESTful APIs for the functionalities mentioned above.
- Include endpoints for ICCID, MSISDN, and IMEI management.
- Document APIs using Swagger for easy consumption.

#### User Interface (React):

- Develop a React-based web user interface.
- Enable users to perform ICCID, MSISDN, and IMEI management tasks.
- Display relevant information, pools, and associations.

### Tracking and Reporting

- **Date Tracking:**
  - Keep a record of reservation and release dates for all reserved numbers.

- **Status and Pool Management:**
  - Maintain the status of ICCID and MSISDN numbers (reserved, available, allocated).
  - Define and manage different pools of numbers (e.g., ICCID pool, MSISDN pool) with associated status.

## Dev/Execution Platform

- **Development Platform:**
  - Utilize Java 11 for the backend development.
  - Implement the backend using Spring Boot 2.7.13.

- **User Interface Technology:**
  - Develop the frontend using React for the web-based user interface.

## Reference Documents/Videos

- Read and understand the requirements of number management in wireless network inventory.

## Remarks

- The application should enforce that MSISDN numbers cannot be reserved indefinitely and must be reverted back to the pool after a specified period.

## Approval

This set of requirements has been approved by:

- **SME Name:** Vignesh Ethiraj
