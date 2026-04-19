FROM node:20-alpine AS build
RUN mkdir /project
COPY . /project
WORKDIR /project
RUN npm ci
RUN npm run build -- --configuration production

FROM nginx:1.25-alpine
COPY --from=build /project/dist/front/browser /usr/share/nginx/html
COPY nginx.conf /etc/nginx/conf.d/default.conf
EXPOSE 80
CMD ["nginx", "-g", "daemon off;"]
