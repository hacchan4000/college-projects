# bahasa yg diterima adlh bhs 01
# a. L1 : semua string yang awalnya 10 dan akhiran 01
# b. L2 : semua string yang mengandung substring 000 dan akhiran 1
# c. L3 : semua string yang awalnya dan akhirannya simbolnya beda
# d. L4 : semua string yang awalnya dan akhirannya simbolnya sama dan mengandung substring 101

class DeterministicFiniteAutomata: #class dfa agar codenya modular
    def __init__(self,Q,inputs,delta,q0,F): # definisiin atribut dari class dfa ato notasi dfa
        self.States = Q # himpunan states
        self.inputs = inputs # himpunan input 0 1
        self.ftransisi = delta # fungsi transisi
        self.stateAwal = q0 # state awal
        self.stateAkhirs = F # himpunan state akhir
    
    def delta_dfa(self,word): #method dfa
        
        currentState = self.stateAwal #nyimpen state skrg, seiring iterasi bakal di updet" trs
        while word !="" : # iterasi input string user jd bakal iterasi trs sampe abis
            try:
                currentState = self.ftransisi[(currentState, word[0])]
                word = word[1:]
            except KeyError:
                # Handle the case where the transition is undefined
                print(f"ketemu dead state")
                return False  # Invalid transition
        return currentState in self.stateAkhirs
    
    def hat_delta_dfa(self,CurrentStates,kata): # method dfa rekursif
        if kata =="": # base case kalo input string kita abis ya return
            return CurrentStates
        next = self.ftransisi[(CurrentStates,kata[0])]# state berikutnya
        return self.hat_delta_dfa(next,kata[1:])# rekursifnya
    

# bahasa L1 , disini ak manggil class dfa trs parameternya ak isi sesuai yg di _init_
L1 = DeterministicFiniteAutomata({0,1,2,3,4,5}, # himpunan states
                                 {"0","1"},# sigma/ inputnya
                                 {(0,"0"):5,(0,"1"):1, # ini fungsi2 transisinya cara bacanya kalo state X dpt input Y bakal ke state Z (X,Y):Z
                                  (1,"0"):2,(1,"1"):5,
                                  (2,"0"):3,(2,"1"):2,
                                  (3,"0"):3,(3,"1"):4,
                                  (4,"0"):3,(4,"1"):3},
                                 0,# state awal
                                 {4}) # himpunan state akhir

L2 = DeterministicFiniteAutomata({0,1,2,3,4},
                                 {"0","1"},
                                 {(0,"0"):1,(0,"1"):0,
                                  (1,"0"):2,(1,"1"):1,
                                  (2,"0"):3,(2,"1"):1,
                                  (3,"0"):3,(3,"1"):4,
                                  (4,"0"):3,(4,"1"):4},
                                 0,
                                 {4})

L3 = DeterministicFiniteAutomata({0,1,2,3,4},
                                 {"0","1"},
                                 {(0,"0"):3,(0,"1"):1,
                                  (1,"0"):2,(1,"1"):1,
                                  (2,"0"):2,(2,"1"):1,
                                  (3,"0"):3,(3,"1"):4,
                                  (4,"0"):3,(4,"1"):4},
                                 0,
                                 {2,4})
L4 = DeterministicFiniteAutomata({0,1,2,3,4,5,6,7,8,9,10},
                                 {"0","1"},
                                 {(0,"0"):6,(0,"1"):1,
                                  (1,"0"):2,(1,"1"):1,
                                  (2,"0"):2,(2,"1"):3,
                                  (3,"0"):4,(3,"1"):1,
                                  (4,"0"):4,(4,"1"):5,
                                  (5,"0"):4,(5,"1"):5,
                                  (6,"0"):0,(6,"1"):7,
                                  (7,"0"):8,(7,"1"):7,
                                  (8,"0"):6,(8,"1"):9,
                                  (9,"0"):10,(9,"1"):9,
                                  (10,"0"):10,(10,"1"):9,},
                                 0,
                                 {5,10})

print(L1.delta_dfa("010000101"))
print(L1.delta_dfa("10000101"))
#print(L2.delta_dfa("1001"))
#print(L3.delta_dfa("1001"))
