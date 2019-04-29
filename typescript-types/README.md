# Slack API TypeScript types generated by @seratch

This npm package is a collection of TypeScript type definition of Slack APIs.

Currently, the package support the following APIs.

* API Methods (web-api)
* Events API (events-api)
* Real Time Messaging API (rtm-api)

## How to use

```typescript
import * as express from 'express';
import { Express, Request, Response } from 'express';

import * as Slack from '@slack/web-api';
import * as Api from 'seratch-slack-types/web-api';
import * as Events from 'seratch-slack-types/events-api';

export const slack = new Slack.WebClient(process.env.SLACK_API_TOKEN);

export const app: Express = express();

app.post('/events', function (req: Request, res: Response) {
  const body = JSON.parse(req.body);

  if (body.type === 'url_verification') {
    // url verification
    res.status(200).write(body.event.challenge);

  } else if (body.type === 'event_callback' && body.event.type === 'message') {

    // message event subscription
    const payload = body as Events.MessagePayload;

    // call web api like this
    // `payload.event.text` here is typesafe
    slack.api.test({ text: payload.event.text }).then((response: Api.ApiTestResponse) => {
      if (response.ok) {
        // so something here
      }
    }).catch((e) => {
      // so something here
    });
  } else {
    // so something here
  }
  res.status(200).json({ message: 'thanks!' });
});

export const dispatcher = require('serverless-http')(app);
```

## How is it generated?

These types are generated from jSlack library's type definitions in Java and actual JSON response data from Slack Platform. If you're interested in the details, take a look at the GitHub repository.

https://github.com/seratch/jslack

The coverage may not be 100% and a portion of the properties may be incorrect. Let @seratch know if you find rooms to improve in this package.
