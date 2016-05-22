FROM mhart/alpine-node:4

WORKDIR /webapp

ADD package.json /webapp/package.json
RUN npm install --quiet

ADD . /webapp

RUN node_modules/gulp/bin/gulp.js build --production

EXPOSE 4070

CMD ["node", "index.js"]
