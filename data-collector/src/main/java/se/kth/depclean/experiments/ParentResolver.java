package se.kth.depclean.experiments;

import org.apache.maven.model.Dependency;
import org.apache.maven.model.Model;
import org.apache.maven.model.Parent;
import org.codehaus.plexus.util.xml.pull.XmlPullParserException;
import se.kth.depclean.experiments.util.PomDownloader;
import se.kth.depclean.experiments.util.PomManipulator;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashSet;

public class ParentResolver {

    public static void main(String[] args) throws IOException, XmlPullParserException {

        String artifactDir = "/home/cesarsv/Documents/papers/2019_papers/royal-debloat/depclean-experiments/data-collector/experiment/artifact/";
        String parentPomDir = "/home/cesarsv/Documents/papers/2019_papers/royal-debloat/depclean-experiments/data-collector/experiment/artifact/parentPom/";

        /* the file to write the status of each dependency */
        BufferedWriter bw = new BufferedWriter(new FileWriter("/home/cesarsv/Documents/papers/2019_papers/royal-debloat/depclean-experiments/data-collector/experiment/isInherited.txt"));

        /* read the results file */
        BufferedReader br = new BufferedReader(new FileReader(new File("/home/cesarsv/Documents/papers/2019_papers/royal-debloat/depclean-experiments/data-collector/experiment/results10K.csv")));

        //read the next unprocessed artifact
        String line = br.readLine(); // read the first line
        line = br.readLine();

        int i = 1;
        // read the list of artifacts' coordinates to be analyzed
        while (line != null) {
            String[] split = line.split(",");
            String artifact = split[0];
            String dependency = split[1];

            String[] artifactSplit = artifact.split(":");
            PomDownloader.downloadPom(artifactDir, artifactSplit[0], artifactSplit[1], artifactSplit[2]);
            Model pomModel = PomManipulator.readModel(new File(artifactDir + "pom.xml"));
            String[] dependencySplit = dependency.split(":");

            HashSet inheritedDependencies = findInheritedDependencies(pomModel, parentPomDir);

            System.out.println(i++);

//            System.out.println(artifact + " ===> " + inheritedDependencies.toString());

            if (inheritedDependencies.contains(dependencySplit[0] + ":" + dependencySplit[1])) {
                bw.write(artifact + ", " + dependency + ", Yes" + "\n");
            } else {
                bw.write(artifact + ", " + dependency + ", No" + "\n");
            }

            String sameArtifact = artifact;
            while (artifact.equals(sameArtifact)) {
                if (inheritedDependencies.contains(dependencySplit[0] + ":" + dependencySplit[1])) {
                    bw.write(artifact + ", " + dependency + ", Yes" + "\n");
                } else {
                    bw.write(artifact + ", " + dependency + ", No" + "\n");
                }
                String[] sameLine = br.readLine().split(",");
                sameArtifact = sameLine[0];
                dependency = sameLine[1];
                dependencySplit = dependency.split(":");
            }

            line = br.readLine();
        }
        bw.close();
        br.close();
    }

    /**
     * Finds all dependencies inherited from the parent pom.
     */
    public static HashSet findInheritedDependencies(Model pomModel, String parentPomDir) throws IOException, XmlPullParserException {
        HashSet inheritedDependencies = new HashSet();
        while (pomModel.getParent() != null) {
            Parent parent = pomModel.getParent();
            try {
            PomDownloader.downloadPom(parentPomDir, parent.getGroupId(), parent.getArtifactId(), parent.getVersion());
            Model parentPomModel = PomManipulator.readModel(new File(parentPomDir + "pom.xml"));


                if (parentPomModel.getDependencyManagement() != null) {
                    for (Dependency dep : parentPomModel.getDependencyManagement().getDependencies()) {
                        String dependency = dep.getGroupId() + ":" + dep.getArtifactId();
                        inheritedDependencies.add(dependency);
                    }
                }

                for (Dependency dep : parentPomModel.getDependencies()) {
                    String dependency = dep.getGroupId() + ":" + dep.getArtifactId();
                    inheritedDependencies.add(dependency);
                }
                pomModel = parentPomModel;
            }catch (Exception e){
                return inheritedDependencies;
            }
        }

        return inheritedDependencies;
    }

}
