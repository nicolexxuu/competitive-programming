#include <bits/stdc++.h>

using namespace std;

int counts[26];

int main() {
	ifstream cin("blocks.in");
	ofstream cout("blocks.out");
	
	int N; // number of cards
	cin >> N;
	
	// for each card, get the number of copies of all letters on both sides
	for(int i = 0; i < N; i++) {
		string word1, word2;
		// store letter counts on side A and side B of card i
		int freq1[26], freq2[26]; 
		fill(freq1, freq1+26, 0);
		fill(freq2, freq2+26, 0);
		
		cin >> word1; // side A
		cin >> word2; // side B
		
		for(char c : word1) 
			freq1[c - 'a']++; // index of the letter in alphabet
		for(char c : word2)
			freq2[c - 'a']++;
			
		// for each letter, add letter counts of card i
		for(int letter = 0; letter < 26; letter++)
			counts[letter] += max(freq1[letter], freq2[letter]);
	}
	
	for(int i =  0; i < 26; i++) 
		cout << counts[i] << endl;
}
