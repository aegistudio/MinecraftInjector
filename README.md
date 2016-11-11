# Minecraft Injector
A bukkit (minecraft server) injector that is version independent.

### Purpose
Using NMS (net minecraft server) suffixed by version makes it unhandy to be compatible with different versions.

McInject is the project to deal with the version issue, providing a unified interface so that other projects could obtain version compatibility easily.

It's currently used by several other projects by mine. Feel free to use it if you want to.

### Re-namespacing
McInject provides features that you could change its namespace via some config.

If you're using maven, add these to your pom with some modification and merging.

			<plugin>
				<artifactId>maven-dependency-plugin</artifactId>
				<version>2.10</version>
				<configuration><artifactItems><artifactItem>
					<groupId>net.aegistudio.mcinject</groupId>
					<artifactId>minecraft-injector</artifactId>
					<!-- version of mc inject -->
					<version>1.0</version>
					<type>jar</type>
					<overWrite>true</overWrite>
					<outputDirectory>${basedir}</outputDirectory>
					<includes>*.xml,*.properties</includes>
				</artifactItem></artifactItems></configuration>
			</plugin>
			
			<plugin>
				<artifactId>maven-antrun-plugin</artifactId>
				<version>1.8</version>
				<executions><execution>
					<id>unpack-mcinject</id>
					<phase>generate-sources</phase>
					<goals><goal>run</goal></goals>
					<configuration><tasks>
						<!-- set mcinject.path to source folder of mcinject -->
						<property name="mcinject.path" value="mcinject"/>
						<!-- set mcinject.reppkg to your own package name -->
						<property name="mcinject.reppkg" value="net.aegistudio.mpp"/>
						<ant antfile="mcinject.xml" inheritAll="true"/>
					</tasks></configuration>
				</execution></executions>
			</plugin>

As <i>dependency:unpack</i> is not included in default lifecycle, you may have to execute it manually.

After running <i>dependency:unpack</i>, two files will be generated in the project base directory, remember to git-ignore them.

And you would like to git-ignore the source folder of mcinject either.