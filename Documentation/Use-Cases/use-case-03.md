# USE CASE: 3. Report showing all countries in a region

## CHARACTERISTIC INFORMATION

### Goal in Context
As a User, I want to be able to view different country reports, so that I can easily see which countries are the most populated within the specified area.


### Scope
The Population Information System


### Level
Primary Task


### Preconditions
User has access to the application.

Database has upto date population data.


### Success End Condition
Report is produced displaying population data to the user.


### Failed End Condition
No report is produced.


### Primary Actor
User


### Trigger
User prompts application for population data of countries in a specific region.


## MAIN SUCCESS SCENARIO
1. User prompts application for population data.
2. Data is returned to user in the form of a report from the highest population to smallest.


## EXTENSIONS
#### 2. Database doesn't contain population data.
Application returns error message to user informing them that the population data is not available.


## SUB-VARIATIONS
None


## SCHEDULE

**DUE DATE**: Release 1.0

