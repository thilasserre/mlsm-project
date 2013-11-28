import random, copy, math, sys
import numpy as np



def distance(x, y):
    return math.sqrt(np.dot(x-y,(x-y).conj()))

def responsability(d):
    return math.exp(-1 * 200.0 * d)


def k_means_soft(t, nbiter, nbclusters=2):
    """ 
    Each row of t is a point, each column their coordonates. posibility of N dimensions.
    'nbclusters' is the number of clusters
    'nbiter' is the number of iterations, In this way we can compute several times the algorithm. If an empty cluster appears, we can run the algorithm an other time. So if nbiter=2, among the two running of the algorithm, one will be non-empty cluster for sure. Warning, the printed result is only the last iteration.
    'distance' is the function to use for comparing observations
    """
    npoints = t.shape[0]
    nbfeatures = t.shape[1]
    # find xranges for each features
    min_max = []
    for f in xrange(nbfeatures):
        min_max.append((t[:,f].min(), t[:,f].max()))

    for f in xrange(nbfeatures):
        t[:,f] -= min_max[f][0]
        t[:,f] /= (min_max[f][1]-min_max[f][0])
    min_max = []
    for f in xrange(nbfeatures):
        min_max.append((t[:,f].min(), t[:,f].max()))

    result = {}
    quality = 0.0 # sum of the means of the distances to centroids
    random.seed()
    dist = np.ndarray([npoints,nbclusters], np.float64) # distance obs<->clust
    resp = np.ndarray([npoints,nbclusters], np.float64) # responsability o<->c
    # iterate for the best quality
    for iteration in xrange(nbiter):
        clusters = [[] for c in xrange(nbclusters)]
        # Step 1: place nbclusters seeds for each features
        centroids = [np.array([random.uniform(min_max[f][0], min_max[f][1])\
                for f in xrange(nbfeatures)], np.float64)\
                for c in xrange(nbclusters)]
        old_centroids = [np.array([-1 for f in xrange(nbfeatures)], np.float64)\
                for c in xrange(nbclusters)]
        new_sum = math.fsum([distance(centroids[c], old_centroids[c])\
                for c in xrange(nbclusters)])
        old_sum = sys.maxint
        np.seterr(invalid='raise')
        # iterate until convergence
        i = 0
        while (new_sum < old_sum and i < 10):
            old_centroids = copy.deepcopy(centroids)
            old_sum = new_sum
            for i in xrange(nbclusters):
                clusters[i] = []
            # precompute distance to all centroids for all observations
            for i in xrange(nbclusters):
                for j in xrange(npoints):
                    dist[j,i] = distance(centroids[i], t[j,:])
            # Step 2: compute the degree of assignment for each point
            for i in xrange(npoints):
                for j in xrange(nbclusters):
                    resp[i,j] = responsability(dist[i,j])
            for i in xrange(npoints):
                resp[i,:] /= math.fsum(resp[i,:])

            # Step 3: recalculate the positions of the nbclusters centroids
            for c in xrange(nbclusters):
                mean = np.array([0 for i in xrange(nbfeatures)], np.float64)

                for i in xrange(npoints):
                    mean += resp[i,c] * t[i,:]
                mean /= (math.fsum(resp[:,c]))

                centroids[c] = np.array(mean, np.float64)
                #print centroids
            new_sum = math.fsum([distance(centroids[c], old_centroids[c])\
                    for c in xrange(nbclusters)])
                #print "(k-means) old and new sum: ", old_sum, new_sum
            i = i + 1
                
        for o in xrange(npoints):
            clusters[dist[o,:].argmin()].append(o)
        quality = math.fsum([math.fsum([dist[o][c] for o in clusters[c]])\
                /(len(clusters[c])+1) for c in xrange(nbclusters)])
        if not quality in result or quality > result['quality']:
            result['quality'] = quality
            result['centroids'] = centroids
            result['clusters'] = clusters
    return result

if __name__ == "__main__":
    x = np.array([[1.0, 1.0], [1.5, 2.0], [3.0, 4.0], [5.0, 7.0], [3.5, 5.0], [4.5, 5.0], [3.5, 4.5]], np.float64)
    # We can see that with the same points from assignment 7, we got the same result.
    results = k_means_soft(x, 3, 2)
    print results['clusters']

