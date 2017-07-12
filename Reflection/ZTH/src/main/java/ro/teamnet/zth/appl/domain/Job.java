package ro.teamnet.zth.appl.domain;

import ro.teamnet.zth.api.annotations.Column;
import ro.teamnet.zth.api.annotations.Id;
import ro.teamnet.zth.api.annotations.Table;

/**
 * Created by Emilia.Palaghita on 12-Jul-17.
 */

@Table(name = "jobs")
public class Job {
    @Id(name = "job_id")
    private Long jobId;

    @Column(name = "job_title")
    private String jobTtile;

    @Column(name = "min_salary")
    private Long minSalary;

    @Column(name = "max_salary")
    private Long maxSalary;

    public Long getJobId() {
        return jobId;
    }

    public void setJobId(Long jobId) {
        this.jobId = jobId;
    }

    public String getJobTtile() {
        return jobTtile;
    }

    public void setJobTtile(String jobTtile) {
        this.jobTtile = jobTtile;
    }

    public Long getMinSalary() {
        return minSalary;
    }

    public void setMinSalary(Long minSalary) {
        this.minSalary = minSalary;
    }

    public Long getMaxSalary() {
        return maxSalary;
    }

    public void setMaxSalary(Long maxSalary) {
        this.maxSalary = maxSalary;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Job job = (Job) o;

        if (!jobId.equals(job.jobId)) return false;
        if (jobTtile != null ? !jobTtile.equals(job.jobTtile) : job.jobTtile != null) return false;
        if (minSalary != null ? !minSalary.equals(job.minSalary) : job.minSalary != null) return false;
        return maxSalary != null ? maxSalary.equals(job.maxSalary) : job.maxSalary == null;
    }

    @Override
    public int hashCode() {
        int result = jobId.hashCode();
        result = 31 * result + (jobTtile != null ? jobTtile.hashCode() : 0);
        result = 31 * result + (minSalary != null ? minSalary.hashCode() : 0);
        result = 31 * result + (maxSalary != null ? maxSalary.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Job{" +
                "jobId=" + jobId +
                ", jobTtile='" + jobTtile + '\'' +
                ", minSalary=" + minSalary +
                ", maxSalary=" + maxSalary +
                '}';
    }
}
