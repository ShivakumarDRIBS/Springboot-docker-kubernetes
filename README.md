docker build -t springboot-app:latest .  -> make sure springboot-app is the image name in deployment.yml fule
kubectl apply -f deployment.yaml
kubectl apply -f service.yml

kubectl logs -f <pod-name>

kubectl scale deployment <deployment-name> --replicas=0  -> to remove the running pod
kubectl delete service <service-name>
