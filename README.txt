Ho scelto di usare la strategia JOINED per l'ereditarietà perché mi sembrava più adatta alla casistica,
essendo che in un caso reale del genere sarebbe stato utile risalire ad una tabella generale che accogliesse
tutte le entities padre-figlio.

Per quanto riguarda users e prestiti, ho fatto in modo che la relazione fosse one to many da users a lendings (un user può avere più
prestiti, mentre ogni prestito è legato ad un solo user). 

Stessa cosa tra pubblicazioni e prestiti in quanto ogni prestito riguarda una sola pubblicazione, mentre ogni pubblicazione
può collezionare più prestiti a suo carico.