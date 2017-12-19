package battleships.init.sequence;

public class SequencesGeneratorBuilder {

    private final Board board;

    SequencesGeneratorBuilder(Board board){
        this.board = board;
    }

    SequencesGeneratorBuilder withCleanFields(){
        Board board = Board.build();
        //top border
        board.getFields().stream()
                .filter(field -> field.getPosition() >= 0 || field.getPosition() <= 9)
                .forEach(field -> field.setState(FieldState.BORDER));
        return new SequencesGeneratorBuilder(board);
    }

    SequencesGeneratorBuilder withBorders(){
        return withCleanFields();
    }



}
