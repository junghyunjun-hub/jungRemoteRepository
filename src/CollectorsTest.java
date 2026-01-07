
import static java.util.stream.Collectors.*;

import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

class Record {
    int studentId;
    int score;
    String subject;

    Record(int studentId, int score, String subject) {
        this.studentId = studentId;
        this.score = score;
        this.subject = subject;
    }
}

public class CollectorsTest {
    public static void main(String[] args) {
        List<Record> records = List.of(
                new Record(1, 90, "Math"),
                new Record(1, 85, "English"),
                new Record(1, 90, "Science"),
                new Record(2, 78, "Math"),
                new Record(2, 78, "English"),
                new Record(2, 92, "Science")
        );

        Map<Integer, Map<Integer, String>> result = records.stream()
                .collect(groupingBy(
                        r -> r.studentId, // 外側のキー: studentId
                        toMap(
                                r -> r.score, // 内側のキー: score
                                r -> r.subject, // 値: subject
                                (s1, s2) -> s1 // 同じスコアが複数あれば最初の subject を採用
                        )
                ));

        result.forEach((studentId, scoreMap) -> {
            System.out.println("Student " + studentId + ":");
            scoreMap.forEach((score, subject) ->
                    System.out.println(" Score " + score + " → " + subject)
            );
        });

        Stream<String> stream = Stream.of("Apple", "Banana", "Cherry");

    // filter は Stream インターフェースの抽象メソッド
      stream.forEach(System.out::println);


        List<String> list = List.of("A", "B", "C");
        Stream<String> stream2 = list.stream(); // 具象クラスのインスタンス

        stream2.filter(s -> s.startsWith("A")) // ← インスタンス・メソッドとして呼び出し可能
                .map(String::toUpperCase)
                .forEach(System.out::println);


    }
}
