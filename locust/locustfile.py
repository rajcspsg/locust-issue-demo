from locust import User,task,constant_throughput,LoadTestShape

# Load shape:
# 1. 100 RPS for 10s
# 2. 100->2500 RPS in 24h (linear: spawns +1 user every 36 seconds for a day)
# 3. Repeat Step 1&2 four more times. Stop after 3 days if results are consistent.

DAY_IN_SECONDS = 24 * 60 * 60

def stages_for_one_day(days):
    warm_up_duration = (DAY_IN_SECONDS * days) + ((days + 1) * 10)
    ramp_up_duration = warm_up_duration + DAY_IN_SECONDS
    return [
        {"duration":60, "users": 2, "spawn_rate": 2},
        {"duration": 60, "users": 3, "spawn_rate": 3},
        {"duration":60, "users": 2, "spawn_rate": 2}
    ]

stages = []
for i in range(5):
    stages.extend(stages_for_one_day(i))

class LinearShape(LoadTestShape):

    def tick(self):
        run_time = self.get_run_time()
        print(run_time)
        return next(((stage["users"], stage["spawn_rate"]) for stage in stages if run_time < stage["duration"]), None)

class DummyUser(User):
    @task(1)
    def dummy(self):
        pass