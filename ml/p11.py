import numpy as np
import pandas as pd
import matplotlib.pyplot as plt
import seaborn as sns
from sklearn.cluster import KMeans
from sklearn.mixture import GaussianMixture
from sklearn.preprocessing import StandardScaler
from sklearn.metrics import silhouette_score
from sklearn.decomposition import PCA

# Load the Heart Disease dataset
dataset_url = "https://archive.ics.uci.edu/ml/machine-learning-databases/heart-disease/processed.cleveland.data"
columns = ["age", "sex", "cp", "trestbps", "chol", "fbs", "restecg", "thalach", "exang", "oldpeak", "slope", "ca", "thal", "target"]
df = pd.read_csv(dataset_url, names=columns, na_values="?")

print(df)

df.dropna(inplace=True)  # Remove missing values

# Separate features from target
X = df.drop(columns=['target'])
scaler = StandardScaler()
X_scaled = scaler.fit_transform(X)

print(X_scaled)

# Determine optimal clusters for K-Means
sse = []
k_range = range(2, 10)
for k in k_range:
    kmeans = KMeans(n_clusters=k, random_state=42)
    kmeans.fit(X_scaled)
    sse.append(kmeans.inertia_)

plt.plot(k_range, sse, marker='o')
plt.xlabel('Number of Clusters')
plt.ylabel('SSE (Elbow Method)')
plt.title('Elbow Method for Optimal K')
plt.show()




# Apply K-Means with optimal k (assume k=3 based on the elbow method)
kmeans = KMeans(n_clusters=3, random_state=42)
kmeans_labels = kmeans.fit_predict(X_scaled)

# Apply Expectation-Maximization (GMM) with optimal components
gmm = GaussianMixture(n_components=3, random_state=42)
gmm_labels = gmm.fit_predict(X_scaled)

# Evaluate clustering quality
silhouette_kmeans = silhouette_score(X_scaled, kmeans_labels)
silhouette_gmm = silhouette_score(X_scaled, gmm_labels)

print(f"Silhouette Score for K-Means: {silhouette_kmeans}")
print(f"Silhouette Score for EM (GMM): {silhouette_gmm}")

# Visualize clusters using PCA
pca = PCA(n_components=2)
X_pca = pca.fit_transform(X_scaled)

plt.figure(figsize=(12, 5))
plt.subplot(1, 2, 1)
sns.scatterplot(x=X_pca[:, 0], y=X_pca[:, 1], hue=kmeans_labels, palette='viridis')
plt.title("K-Means Clustering")

plt.subplot(1, 2, 2)
sns.scatterplot(x=X_pca[:, 0], y=X_pca[:, 1], hue=gmm_labels, palette='coolwarm')
plt.title("EM (GMM) Clustering")
plt.show()
