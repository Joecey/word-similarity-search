# Word Similarity Search (Java 17)
Word similarity search created with **Java 17** to allow users to rank words or phrases based on a 50d vector dataset. 
This project allows users to provide a [**50d vector dataset of word embeddings**](https://nlp.stanford.edu/projects/glove/) 
and a word/phrase they would like to parse. From there, the program provides the highest ranked words based on similarity 
using an approach of the user's choosing.

## State-of-the-Art Features
1. Along with the default 50d dataset we provide (.word-embeddings.txt), specify your own 50d dataset
2. Parse a single word or an entire sentence, and the system will try to provide the best result for you
3. Save your findings in your own specified output file and share it with your friends

The available algorithms include:
- Dot Product
- Euclidean Distance
- Cosine Distance

## How To Run 
**Ensure that you are have Java SDK 17 or higher installed**

First compile the `src/` directory using the following command 

`javac src/ie/atu/sw/*.java -d out/`

Then run the program with  
`cd out/`

`java out.ie.atu.sw.Runner`

You will then be presented with options as follows:
1) **Provide file path for 50d word embeddings dataset**: Chose the 50d .txt file to act as your model

2) **Print total count of words in model**: View total amount of words in model

3) **Provide file path for output**: Specify the output file for your results

4) **Cycle similarity search algorithm**: Cycle through the available algorithms mentioned above

5) **Change number of words to show in similarity ranking**: Change the number of words shown in the output file
   
6) **Enable/Disable weight details (false)**: Toggle word similarity score visibility 
    
7) **Begin word similarity search**: Input word sequence (e.g. apple banana cheese) and start word similarity search
    
8) **Quit**: Exit the application
   
