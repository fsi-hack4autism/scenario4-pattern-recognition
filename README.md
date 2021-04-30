# scenario4-pattern-recognition

The use case is divided into 2 parts - UI to take in user inputs and visualize the analysis. 
The backend portion connects to LineDanceAI and analyze a test sequence with a benchmark sequence.

The analysis contains the similarity metrics that helps the therapist to compare the behaviour.

The therapist can then make a commentary and save the results.

The results are saved into Cosmos DB.

There is an API to download the saved data for further analysis.

