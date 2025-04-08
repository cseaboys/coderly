import java.util.*;

public class Exp10_SimpleNaiveBayes {
    Map<String, Map<String, Integer>> wordCounts = new HashMap<>();
    Map<String, Integer> classCounts = new HashMap<>();
    int totalDocs = 0;

    void train(List<Doc> data) {
        for (Doc d : data) {
            totalDocs++;
            classCounts.merge(d.label, 1, Integer::sum);
            Map<String, Integer> m = wordCounts.computeIfAbsent(d.label, k -> new HashMap<>());
            for (String w : d.words)
                m.merge(w, 1, Integer::sum);
        }
    }

    String predict(Doc d) {
        double best = -Double.MAX_VALUE;
        String bestClass = null;
        for (String cls : classCounts.keySet()) {
            double prob = Math.log(classCounts.get(cls)) - Math.log(totalDocs);
            Map<String, Integer> m = wordCounts.get(cls);
            for (String w : d.words)
                prob += Math.log(m.getOrDefault(w, 0) + 1);
            if (prob > best) {
                best = prob;
                bestClass = cls;
            }
        }
        return bestClass;
    }

    void evaluate(List<Doc> data) {
        int correct = 0, tp = 0, fp = 0, fn = 0;
        for (Doc d : data) {
            String p = predict(d);
            if (p.equals(d.label)) {
                correct++;
                if ("positive".equals(p))
                    tp++;
            } else {
                if ("positive".equals(p))
                    fp++;
                else
                    fn++;
            }
        }
        System.out.println("Accuracy: " + (double) correct / data.size());
        System.out.println("Precision: " + (double) tp / (tp + fp));
        System.out.println("Recall: " + (double) tp / (tp + fn));
    }

    static class Doc {
        String label;
        List<String> words;

        Doc(String l, String t) {
            label = l;
            words = Arrays.asList(t.split("\\s+"));
        }
    }

    public static void main(String[] args) {
        List<Doc> train = Arrays.asList(
                new Doc("positive", "good happy awesome"),
                new Doc("negative", "bad horrible sad"),
                new Doc("positive", "great awesome good"),
                new Doc("negative", "terrible sad bad"));
        List<Doc> test = Arrays.asList(
                new Doc("positive", "good great awesome"),
                new Doc("negative", "bad terrible horrible"));
        Exp10_SimpleNaiveBayes snb = new Exp10_SimpleNaiveBayes();
        snb.train(train);
        snb.evaluate(test);
    }
}
