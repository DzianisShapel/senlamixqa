pipeline{
	agent any
	stages{
		stage('Git checkout') {
				steps {
					git branch: '${branch}', url: 'https://github.com/DzianisShapel/senlamixqa.git'
				}
		}
		stage('Test') {
				steps {
					bat './gradlew clean test'
				}
		}

    }
}