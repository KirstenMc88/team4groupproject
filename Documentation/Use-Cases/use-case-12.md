# USE CASE: 12. Report showing top x populated cities in the world

## CHARACTERISTIC INFORMATION

### Goal in Context
As a Data Analyst, I want to be able to view different city reports, so that I can easily see which cities are the most populated within the specified area.


### Scope
The Population Information System


### Level
Primary Task


### Preconditions
Data Analyst has access to the application.

Database has upto date population data.


### Success End Condition
Report is produced displaying population data to the Data Analyst.


### Failed End Condition
No report is produced.


### Primary Actor
Data Analyst


### Trigger
Data Analyst prompts application for population data of a specified number of cities in the world.


## MAIN SUCCESS SCENARIO
1. Data Analyst prompts application for population data.
2. Data is returned to Data Analyst in the form of a report from the highest population to smallest.


## EXTENSIONS
#### 2. Database doesn't contain population data.
Application returns error message to Data Analyst informing them that the population data is not available.


## SUB-VARIATIONS
None


## SCHEDULE

**DUE DATE**: Release 1.0

