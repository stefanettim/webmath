  git clone https://github.com/stefanettim/webmath.git  
  podman build -t stefanettim:webmath -f webmath/w.dockerfile  
  podman run -p 8080:8080 localhost/stefanettim:webmath  

http://localhost:8080/w/a/  
