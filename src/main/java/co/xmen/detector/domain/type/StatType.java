package co.xmen.detector.domain.type;

public enum StatType {
    MUTANTS(1);

    private int key;

    StatType(int key){
        this.key = key;
    }

    public int id(){
        return key;
    }
}
