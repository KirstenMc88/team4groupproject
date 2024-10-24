# USE CASE: 18. Report showing all capital cities in a continent

## CHARACTERISTIC INFORMATION

### Goal in Context
As a Data Analyst, I want to be able to view different capital city reports, so that I can easily see which capital cities are the most populated within the specified area.


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
Data Analyst prompts application for the population data of all capital cities in a specified continent.


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
